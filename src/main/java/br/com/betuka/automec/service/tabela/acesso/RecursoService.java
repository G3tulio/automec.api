package br.com.betuka.automec.service.tabela.acesso;

import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.acesso.RecursoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.acesso.RecursoEntity;
import br.com.betuka.automec.repository.tabela.acesso.RecursoRepository;
import br.com.betuka.automec.util.enums.SituacaoEnum;

@Service
public class RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;
	
	public List<RecursoDTO> listar() {
		return RecursoDTO.toList(this.recursoRepository.findAll());
	}
	
	private void validarEntrada(RecursoDTO recursoDTO) throws ValidationException, Exception {
    	if (recursoDTO.getCodRecurso() != 0) { 
    		this.pesquisarCodigo(recursoDTO.getCodRecurso()); // Caso não encontre levanta ValidationException
    	}
		
		if (recursoDTO.getDesRecurso().isBlank()) {
			throw new ValidationException(Constants.RECURSO_DESCRICAO_OBRIGATORIA);
		}
		
		if (! EnumSet.of(SituacaoEnum.A, SituacaoEnum.C).contains(recursoDTO.getSituacao()) ) {
			throw new ValidationException(Constants.RECURSO_SITUACAO_INEXISTENTE);
		}
		
		RecursoDTO oRecursoDTO = null;
		
		try {
			oRecursoDTO = this.pesquisarDescricao(recursoDTO.getDesRecurso());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oRecursoDTO)) {
			if (recursoDTO.getCodRecurso() == 0) {
				throw new ValidationException(Constants.RECURSO_JA_CADASTRADO);
			} else {
				if (recursoDTO.getCodRecurso() != oRecursoDTO.getCodRecurso()) {
					throw new ValidationException(Constants.RECURSO_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(RecursoDTO recursoDTO ) throws ValidationException, Exception {
		this.validarEntrada(recursoDTO);
		this.recursoRepository.save(new RecursoEntity(recursoDTO));
	}
	
	public void deletar(int codRecurso) throws ValidationException, Exception {
		this.pesquisarCodigo(codRecurso); // Caso não encontre levanta ValidationException
		
		// Mais a frente, antes de excluir verificar se o recurso está sendo usando em uma [ FK ]
		
    	try {
    		this.recursoRepository.deleteById(codRecurso);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public RecursoDTO pesquisarCodigo(int codRecurso) throws ValidationException, Exception {
    	try {
    		return new RecursoDTO(this.recursoRepository.findById(codRecurso).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.RECURSO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
    
    public RecursoDTO pesquisarDescricao(String desRecurso) throws ValidationException, Exception {
    	try {
    		return new RecursoDTO(this.recursoRepository.pesquisarDescricao(desRecurso).get());
    	} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.RECURSO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    public List<RecursoDTO> buscarDescricao(String desRecurso) throws ValidationException, Exception {
    	try {
    		return RecursoDTO.toList(this.recursoRepository.buscarDescricao(desRecurso));
    	} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
}
