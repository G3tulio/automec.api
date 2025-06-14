package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.MarcaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.MarcaEntity;
import br.com.betuka.automec.repository.tabela.apoio.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;
	
	public List<MarcaDTO> listar() {
		return MarcaDTO.toList(this.marcaRepository.findAll());
	}
	
	public void validarEntrada(MarcaDTO marcaDTO) throws ValidationException, Exception {
		if (marcaDTO.getCodMarca() != 0) {
			this.pesquisarCodigo(marcaDTO.getCodMarca()); // Caso não encontre já levanta uma Exception
		}
		
		if (marcaDTO.getDesMarca().isEmpty()) {
			throw new ValidationException(Constants.MARCA_DESCRICAO_OBRIGATORIA);
		}
		
		MarcaDTO oMarcaDTO = null;
		
		try {
			oMarcaDTO = this.pesquisarDescricao(marcaDTO.getDesMarca());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oMarcaDTO)) {
			if (marcaDTO.getCodMarca() == 0) {
				throw new ValidationException(Constants.MARCA_JA_CADASTRADA);
			} else {
				if (marcaDTO.getCodMarca() != oMarcaDTO.getCodMarca()) {
					throw new ValidationException(Constants.MARCA_JA_CADASTRADA);
				}
			}
		}
	}
	
	public void gravar(MarcaDTO marcaDTO) throws ValidationException, Exception {
		this.validarEntrada(marcaDTO);
		this.marcaRepository.save( new MarcaEntity(marcaDTO) );
	}
    
    private boolean existeModeloMarca(int codMarca) throws ValidationException, Exception {
    	try {
    		return this.marcaRepository.existeModeloMarca(codMarca);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
	
	public void deletar(int codMarca) throws ValidationException, Exception {
		this.pesquisarCodigo(codMarca); // Caso não encontre levanta uma ValidationException
		
		if (this.existeModeloMarca(codMarca)) {
			throw new ValidationException(Constants.MARCA_UTILIZADA);
		}
		
		try {
			this.marcaRepository.deleteById(codMarca);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public MarcaDTO pesquisarCodigo(int codMarca) throws ValidationException, Exception {
		try {
			return new MarcaDTO(this.marcaRepository.findById(codMarca).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public MarcaDTO pesquisarDescricao(String desMarca) throws ValidationException, Exception {
		try {
			return new MarcaDTO(this.marcaRepository.pesquisarDescricao(desMarca).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<MarcaDTO> buscarDescricao(String desMarca) throws ValidationException, Exception {
		try {
			return MarcaDTO.toList(this.marcaRepository.buscarDescricao(desMarca));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
