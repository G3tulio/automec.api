package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.ComponenteDTO;
import br.com.betuka.automec.dto.tabela.apoio.VWComponentesSistemaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.ComponenteEntity;
import br.com.betuka.automec.repository.tabela.apoio.ComponenteRepository;

@Service
public class ComponenteService {

	@Autowired
	private ComponenteRepository componenteRepository;
	
	@Autowired
	private SistemaService sistemaService;
	
	public List<ComponenteDTO> listar() {
		return ComponenteDTO.toList(this.componenteRepository.findAll());
	}
	
	private void validarEntrada(ComponenteDTO componenteDTO) throws ValidationException, Exception {
		if (componenteDTO.getCodComponente() != 0) {
			this.pesquisarCodigo(componenteDTO.getCodComponente()); // Caso não encontre levanta ValidationException
		}
		
		if (componenteDTO.getDesComponente().isBlank()) {
			throw new ValidationException(Constants.COMPONENTE_DESCRICAO_OBRIGATORIA);
		}
		
		if (componenteDTO.getSistema().getCodSistema() == 0) {
			throw new ValidationException(Constants.SISTEMA_CODIGO_N_INFORMADO);
		}
		
		this.sistemaService.pesquisarCodigo(componenteDTO.getSistema().getCodSistema());
		
		ComponenteDTO oComponenteDTO = null;
		
		try {
			oComponenteDTO = this.pesquisarDescricao(componenteDTO.getDesComponente());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oComponenteDTO)) {
			if (componenteDTO.getCodComponente() == 0) {
				throw new ValidationException(Constants.COMPONENTE_JA_CADASTRADO);
			} else {
				if (componenteDTO.getCodComponente() != oComponenteDTO.getCodComponente()) {
					throw new ValidationException(Constants.COMPONENTE_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(ComponenteDTO componenteDTO) throws ValidationException, Exception {
		this.validarEntrada(componenteDTO);
		this.componenteRepository.save( new ComponenteEntity(componenteDTO) );
	}
	
	public void deletar(int codComponente) throws ValidationException, Exception {
		this.pesquisarCodigo(codComponente); // Caso não encontre levanta ValidationException
		
//    	if (this.existeVeiculoComponente(codComponente)) {
//    		throw new ValidationException (Constants.COMPONENTE_UTILIZADO);
//    	}
		
		try {
			this.componenteRepository.deleteById(codComponente);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
//	private boolean existeVeiculoComponente(int codComponente) throws ValidationException, Exception {
//		try {
//			return this.componenteRepository.existeVeiculoComponente(codComponente);
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	}
	
	public ComponenteDTO pesquisarCodigo(int codComponente) throws ValidationException, Exception {
		try {
			return new ComponenteDTO(this.componenteRepository.findById(codComponente).get());
		}  catch (NoSuchElementException e) {
			throw new ValidationException(Constants.COMPONENTE_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ComponenteDTO pesquisarDescricao(String desComponente) throws ValidationException, Exception {
		try {
			return new ComponenteDTO(this.componenteRepository.pesquisarDescricao(desComponente).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.COMPONENTE_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<VWComponentesSistemaDTO> buscarComponentesPorSistema(int codSistema) throws ValidationException, Exception {
		try {
			return this.componenteRepository.buscarComponentesPorSistema(codSistema).get();
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.SISTEMA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
