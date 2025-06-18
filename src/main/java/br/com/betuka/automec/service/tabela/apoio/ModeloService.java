package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.MarcaDTO;
import br.com.betuka.automec.dto.tabela.apoio.ModeloDTO;
import br.com.betuka.automec.dto.tabela.apoio.VWModelosMarcaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.ModeloEntity;
import br.com.betuka.automec.repository.tabela.apoio.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private MarcaService marcaService;
	
	public List<ModeloDTO> listar() {
		return ModeloDTO.toList(this.modeloRepository.findAll());
	}
	
	private void validarEntrada(ModeloDTO modeloDTO) throws ValidationException, Exception {
		if (modeloDTO.getCodModelo() != 0) {
			this.pesquisarCodigo(modeloDTO.getCodModelo()); // Caso não encontre levanta ValidationException
		}
		
		if (modeloDTO.getDesModelo().isBlank()) {
			throw new ValidationException(Constants.MODELO_DESCRICAO_OBRIGATORIA);
		}
		
		if (modeloDTO.getMarca().getCodMarca() == 0) {
			throw new ValidationException(Constants.MARCA_CODIGO_N_INFORMADO);
		}
		
		this.marcaService.pesquisarCodigo(modeloDTO.getMarca().getCodMarca());
		
		ModeloDTO oModeloDTO = null;
		
		try {
			oModeloDTO = this.pesquisarDescricao(modeloDTO.getDesModelo());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oModeloDTO)) {
			if (modeloDTO.getCodModelo() == 0) {
				throw new ValidationException(Constants.MODELO_JA_CADASTRADO);
			} else {
				if (modeloDTO.getCodModelo() != oModeloDTO.getCodModelo()) {
					throw new ValidationException(Constants.MODELO_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(ModeloDTO modeloDTO) throws ValidationException, Exception {
		this.validarEntrada(modeloDTO);
		this.modeloRepository.save( new ModeloEntity(modeloDTO) );
	}
	
	public void deletar(int codModelo) throws ValidationException, Exception {
		this.pesquisarCodigo(codModelo); // Caso não encontre levanta ValidationException
		
    	if (this.existeVeiculoModelo(codModelo)) {
    		throw new ValidationException (Constants.MODELO_UTILIZADO);
    	}
		
		try {
			this.modeloRepository.deleteById(codModelo);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private boolean existeVeiculoModelo(int codModelo) throws ValidationException, Exception {
		try {
			return this.modeloRepository.existeVeiculoModelo(codModelo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ModeloDTO pesquisarCodigo(int codModelo) throws ValidationException, Exception {
		try {
			return new ModeloDTO(this.modeloRepository.findById(codModelo).get());
		}  catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MODELO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ModeloDTO pesquisarDescricao(String desModelo) throws ValidationException, Exception {
		try {
			return new ModeloDTO(this.modeloRepository.pesquisarDescricao(desModelo).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MODELO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<VWModelosMarcaDTO> buscarModelosPorMarca(int codMarca) throws ValidationException, Exception {
		try {
			return this.modeloRepository.buscarModelosPorMarca(codMarca).get();
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
