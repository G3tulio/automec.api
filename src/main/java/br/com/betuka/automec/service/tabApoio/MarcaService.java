package br.com.betuka.automec.service.tabApoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.TabApoio.MarcaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabApoio.MarcaEntity;
import br.com.betuka.automec.repository.tabApoio.MarcaRepository;

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
				throw new ValidationException(Constants.MARCA_JA_CADASTRADO);
			} else {
				if (marcaDTO.getCodMarca() != oMarcaDTO.getCodMarca()) {
					throw new ValidationException(Constants.MARCA_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(MarcaDTO marcaDTO) throws ValidationException, Exception {
		this.validarEntrada(marcaDTO);
		this.marcaRepository.save( new MarcaEntity(marcaDTO) );
	}
    
    private boolean existeMarca(int codMarca) throws ValidationException, Exception {
    	try {
    		return this.marcaRepository.existeMarca(codMarca);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
	
	public void deletar(int codMarca) throws ValidationException, Exception {
		this.pesquisarCodigo(codMarca); // Caso não encontre levanta uma ValidationException
		
		if (this.existeMarca(codMarca)) {
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
	
	public List<MarcaDTO> pesquisarPorDescricao(String desMarca) throws ValidationException, Exception {
		try {
			return MarcaDTO.toList(this.marcaRepository.pesquisarPorDescricao(desMarca));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
