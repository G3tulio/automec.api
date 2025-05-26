package br.com.betuka.automec.service.tabApoio;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.TabApoio.ModeloDTO;
import br.com.betuka.automec.dto.TabApoio.VWModelosMarcaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabApoio.ModeloEntity;
import br.com.betuka.automec.repository.tabApoio.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private MarcaService marcaService;
	
	public List<ModeloDTO> listar() {
		List<ModeloEntity> lista = this.modeloRepository.findAll();
		return lista.stream().map(ModeloDTO::new).toList();
	}
	
	private void validarEntrada(ModeloDTO pModeloDTO) throws ValidationException, Exception {
		if (pModeloDTO.getCodModelo() != 0) {
			this.pesquisarCodigo(pModeloDTO.getCodModelo()); // Caso não encontre já levanta uma Exception
		}
		
		if (pModeloDTO.getDesModelo().isEmpty()) {
			throw new ValidationException(Constants.MODELO_DESCRICAO_OBRIGATORIA);
		}
		
		if (pModeloDTO.getMarca().getCodMarca() == 0) {
			throw new ValidationException(Constants.MARCAR_CODIGO_N_INFRORMADO);
		}
		
		this.marcaService.pesquisarCodigo(pModeloDTO.getMarca().getCodMarca());
	}
	
	public void adicionar(ModeloDTO modeloDTO) throws ValidationException, Exception {
		this.validarEntrada(modeloDTO);
		this.modeloRepository.save( new ModeloEntity(modeloDTO) );
	}
	
	public void atualizar(ModeloDTO modeloDTO) throws ValidationException, Exception {
		this.validarEntrada(modeloDTO);
		this.modeloRepository.save( new ModeloEntity(modeloDTO) );
	}
	
	public void deletar(int codModelo) throws ValidationException, Exception {
		this.pesquisarCodigo(codModelo); // Caso não encontre levanta uma ValidationException
		
		try {
			this.modeloRepository.deleteById(codModelo);	
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
	
	public List<VWModelosMarcaDTO> listarModelosMarca(int codMarca) throws ValidationException, Exception {
		try {
			return this.modeloRepository.listarModelosMarca(codMarca).get();
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
