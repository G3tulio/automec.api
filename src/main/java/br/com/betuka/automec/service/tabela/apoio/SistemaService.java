package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.SistemaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.SistemaEntity;
import br.com.betuka.automec.repository.tabela.apoio.SistemaRepository;

@Service
public class SistemaService {

	@Autowired
	private SistemaRepository sistemaRepository;
	
	public List<SistemaDTO> listar() {
		return SistemaDTO.toList(this.sistemaRepository.findAll());
	}
	
	public void validarEntrada(SistemaDTO sistemaDTO) throws ValidationException, Exception {
		if (sistemaDTO.getCodSistema() != 0) {
			this.pesquisarCodigo(sistemaDTO.getCodSistema()); // Caso não encontre já levanta uma Exception
		}
		
		if (sistemaDTO.getDesSistema().isEmpty()) {
			throw new ValidationException(Constants.SISTEMA_DESCRICAO_OBRIGATORIA);
		}
		
		SistemaDTO oSistemaDTO = null;
		
		try {
			oSistemaDTO = this.pesquisarDescricao(sistemaDTO.getDesSistema());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oSistemaDTO)) {
			if (sistemaDTO.getCodSistema() == 0) {
				throw new ValidationException(Constants.SISTEMA_JA_CADASTRADA);
			} else {
				if (sistemaDTO.getCodSistema() != oSistemaDTO.getCodSistema()) {
					throw new ValidationException(Constants.SISTEMA_JA_CADASTRADA);
				}
			}
		}
	}
	
	public void gravar(SistemaDTO sistemaDTO) throws ValidationException, Exception {
		this.validarEntrada(sistemaDTO);
		this.sistemaRepository.save( new SistemaEntity(sistemaDTO) );
	}
	
	public void deletar(int codSistema) throws ValidationException, Exception {
		this.pesquisarCodigo(codSistema); // Caso não encontre levanta uma ValidationException
		
		// Mais a frente verificar se a categória está relacionada a peça e ou subsistemas
		
		try {
			this.sistemaRepository.deleteById(codSistema);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public SistemaDTO pesquisarCodigo(int codSistema) throws ValidationException, Exception {
		try {
			return new SistemaDTO(this.sistemaRepository.findById(codSistema).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.SISTEMA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public SistemaDTO pesquisarDescricao(String desSistema) throws ValidationException, Exception {
		try {
			return new SistemaDTO(this.sistemaRepository.pesquisarDescricao(desSistema).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.SISTEMA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<SistemaDTO> buscarDescricao(String desSistema) throws ValidationException, Exception {
		try {
			return SistemaDTO.toList(this.sistemaRepository.buscarDescricao(desSistema));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
