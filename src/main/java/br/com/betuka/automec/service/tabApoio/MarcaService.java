package br.com.betuka.automec.service.tabApoio;

import java.util.List;
import java.util.NoSuchElementException;

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
	
	public void validarEntrada(MarcaDTO pMarcaDTO) throws ValidationException, Exception {
		if (pMarcaDTO.getCodMarca() != 0) {
			this.pesquisarCodigo(pMarcaDTO.getCodMarca()); // Caso não encontre já levanta uma Exception
		}
		
		if (pMarcaDTO.getDesMarca().isEmpty()) {
			throw new ValidationException(Constants.MARCA_DESCRICAO_OBRIGATORIA);
		}
	}
	
	public void adicionar(MarcaDTO pMarcaDTO) throws ValidationException, Exception {
		this.validarEntrada(pMarcaDTO);
		this.marcaRepository.save( new MarcaEntity(pMarcaDTO) );
	}
	
	public void atualizar(MarcaDTO pMarcaDTO) throws ValidationException, Exception {
		this.validarEntrada(pMarcaDTO);
		this.marcaRepository.save( new MarcaEntity(pMarcaDTO) );
	}
	
	public void deletar(int codMarca) throws ValidationException, Exception {
		this.pesquisarCodigo(codMarca); // Caso não encontre levanta uma ValidationException
		
		try {
			this.marcaRepository.deleteById(codMarca);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public MarcaDTO pesquisarCodigo(int pCodMarca) throws ValidationException, Exception {
		try {
			return new MarcaDTO(this.marcaRepository.findById(pCodMarca).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public MarcaDTO pesquisarDescricao(String pDesMarca) throws ValidationException, Exception {
		try {
			return new MarcaDTO(this.marcaRepository.pesquisarDescricao(pDesMarca).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MARCA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
