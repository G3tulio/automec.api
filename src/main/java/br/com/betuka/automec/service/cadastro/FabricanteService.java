package br.com.betuka.automec.service.cadastro;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.FabricanteDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.FabricanteEntity;
import br.com.betuka.automec.repository.cadastro.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public List<FabricanteDTO> listar() {
		return FabricanteDTO.toList(this.fabricanteRepository.findAll());
	}
	
	private void validarEntrada(FabricanteDTO fabricanteDTO) throws ValidationException, Exception {
		if (fabricanteDTO.getCodFabricante() != 0) {
			this.pesquisarCodigo(fabricanteDTO.getCodFabricante());
		}
		
		if (fabricanteDTO.getDesFabricante().isBlank()) {
			throw new ValidationException(Constants.FABRICANTE_DESCRICAO_OBRIGATORIA);
		}
		
		FabricanteDTO oFabricanteDTO = null;
		
		try {
			oFabricanteDTO = this.pesquisarDescricao(fabricanteDTO.getDesFabricante());
		} catch (ValidationException e) {
			// Não tem exception, neste caso
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oFabricanteDTO)) {
			if (fabricanteDTO.getCodFabricante() == 0) {
				throw new ValidationException(Constants.FABRICANTE_JA_CADASTRADO);
			} else {
				if (fabricanteDTO.getCodFabricante() != oFabricanteDTO.getCodFabricante()) {
					throw new ValidationException(Constants.FABRICANTE_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(FabricanteDTO fabricanteDTO) throws ValidationException, Exception {
		this.validarEntrada(fabricanteDTO);
		this.fabricanteRepository.save(new FabricanteEntity(fabricanteDTO));
	}
	
	public void deletar(int codFabricante) throws ValidationException, Exception {
		this.pesquisarCodigo(codFabricante);
		
		// Mais a frente verificar se o fabricante tem relação com a entidade peça [ FK ]
		
		try {
			this.fabricanteRepository.deleteById(codFabricante);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public FabricanteDTO pesquisarCodigo(int codFabricante) throws ValidationException, Exception {
		try {
			return new FabricanteDTO(this.fabricanteRepository.findById(codFabricante).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.FABRICANTE_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public FabricanteDTO pesquisarDescricao(String desFabricante) throws Exception {
		try {
			return new FabricanteDTO(this.fabricanteRepository.pesquisarDescricao(desFabricante).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.FABRICANTE_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<FabricanteDTO> buscarDescricao(String desFabricante) throws Exception {
		try {
			return FabricanteDTO.toList(this.fabricanteRepository.buscarDescricao(desFabricante));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
