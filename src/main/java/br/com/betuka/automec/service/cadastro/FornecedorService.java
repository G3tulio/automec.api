package br.com.betuka.automec.service.cadastro;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.FornecedorDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.FornecedorEntity;
import br.com.betuka.automec.repository.cadastro.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public List<FornecedorDTO> listar() {
		return FornecedorDTO.toList(this.fornecedorRepository.findAll());
	}
	
	private void validarEntrada(FornecedorDTO fornecedorDTO) throws ValidationException, Exception {
		if (fornecedorDTO.getCodFornecedor() != 0) {
			this.pesquisarCodigo(fornecedorDTO.getCodFornecedor());
		}
		
		if (fornecedorDTO.getDesFornecedor().isBlank()) {
			throw new ValidationException(Constants.FORNECEDOR_DESCRICAO_OBRIGATORIA);
		}
		
		FornecedorDTO oFornecedorDTO = null;
		
		try {
			oFornecedorDTO = this.pesquisarDescricao(fornecedorDTO.getDesFornecedor());
		} catch (ValidationException e) {
			// Não tem exception, neste caso
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oFornecedorDTO)) {
			if (fornecedorDTO.getCodFornecedor() == 0) {
				throw new ValidationException(Constants.FORNECEDOR_JA_CADASTRADO);
			} else {
				if (fornecedorDTO.getCodFornecedor() != oFornecedorDTO.getCodFornecedor()) {
					throw new ValidationException(Constants.FORNECEDOR_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(FornecedorDTO fornecedorDTO) throws ValidationException, Exception {
		this.validarEntrada(fornecedorDTO);
		this.fornecedorRepository.save(new FornecedorEntity(fornecedorDTO));
	}
	
	public void deletar(int codFornecedor) throws ValidationException, Exception {
		this.pesquisarCodigo(codFornecedor);
		
		// Mais a frente verificar se o fornecedor tem relação com a entidade peça [ FK ]
		
		try {
			this.fornecedorRepository.deleteById(codFornecedor);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public FornecedorDTO pesquisarCodigo(int codFornecedor) throws ValidationException, Exception {
		try {
			return new FornecedorDTO(this.fornecedorRepository.findById(codFornecedor).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.FORNECEDOR_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public FornecedorDTO pesquisarDescricao(String desFornecedor) throws Exception {
		try {
			return new FornecedorDTO(this.fornecedorRepository.pesquisarDescricao(desFornecedor).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.FORNECEDOR_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<FornecedorDTO> buscarDescricao(String desFornecedor) throws Exception {
		try {
			return FornecedorDTO.toList(this.fornecedorRepository.buscarDescricao(desFornecedor));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
