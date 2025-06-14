package br.com.betuka.automec.service.tabAcesso;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabAcesso.PerfilDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabAcesso.PerfilEntity;
import br.com.betuka.automec.repository.tabAcesso.PerfilRepository;

@Service
public class PerfilService {
		
	@Autowired
	private PerfilRepository perfilRepository;
    
    public List<PerfilDTO> listar() {
        return PerfilDTO.toList(this.perfilRepository.findAll());
    }
	
	private void validarEntrada(PerfilDTO perfilDTO) throws ValidationException, Exception {
    	if (perfilDTO.getCodPerfil() != 0) {
    		this.pesquisarCodigo(perfilDTO.getCodPerfil()); // Caso não encontre levanta ValidationException
    	}
		
		if (perfilDTO.getDesPerfil().isBlank()) {
			throw new ValidationException(Constants.PERFIL_DESCRICAO_OBRIGATORIA);
		}
		
		PerfilDTO oPerfilDTO = null;
		
		try {
			oPerfilDTO = this.pesquisarDescricao(perfilDTO.getDesPerfil());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oPerfilDTO)) {
			if (perfilDTO.getCodPerfil() == 0) {
				throw new ValidationException(Constants.PERFIL_JA_CADASTRADO);
			} else {
				if (perfilDTO.getCodPerfil() != oPerfilDTO.getCodPerfil()) {
					throw new ValidationException(Constants.PERFIL_JA_CADASTRADO);
				}
			}
		}
	}
	
    public void gravar(PerfilDTO perfilDTO) throws ValidationException, Exception {
    	this.validarEntrada(perfilDTO);
    	this.perfilRepository.save(new PerfilEntity(perfilDTO));
    }
    
    public void deletar(int codPerfil) throws ValidationException, Exception {
    	this.pesquisarCodigo(codPerfil); // Caso não encontre levanta ValidationException
    	
    	if (this.existePerfilUsuario(codPerfil)) {
    		throw new ValidationException (Constants.PERFIL_UTILIZADO);
    	}
    	
    	try {
    		this.perfilRepository.deleteById(codPerfil);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    private boolean existePerfilUsuario(int codPerfil) throws ValidationException, Exception {
    	try {
    		return this.perfilRepository.existePerfilUsuario(codPerfil);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    public PerfilDTO pesquisarCodigo(int codPerfil) throws ValidationException, Exception {
    	try {
    		return new PerfilDTO(this.perfilRepository.findById(codPerfil).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PERFIL_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    public PerfilDTO pesquisarDescricao(String desPerfil) throws ValidationException, Exception {
    	try {
    		return new PerfilDTO(this.perfilRepository.pesquisarDescricao(desPerfil).get());
    	} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PERFIL_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    public List<PerfilDTO> buscarDescricao(String desPerfil) throws ValidationException, Exception {
    	try {
    		return PerfilDTO.toList(this.perfilRepository.buscarDescricao(desPerfil));
    	} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
}
