package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.CategoriaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.CategoriaEntity;
import br.com.betuka.automec.repository.tabela.apoio.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<CategoriaDTO> listar() {
		return CategoriaDTO.toList(this.categoriaRepository.findAll());
	}
	
	public void validarEntrada(CategoriaDTO categoriaDTO) throws ValidationException, Exception {
		if (categoriaDTO.getCodCategoria() != 0) {
			this.pesquisarCodigo(categoriaDTO.getCodCategoria()); // Caso não encontre já levanta uma Exception
		}
		
		if (categoriaDTO.getDesCategoria().isEmpty()) {
			throw new ValidationException(Constants.CATEGORIA_DESCRICAO_OBRIGATORIA);
		}
		
		CategoriaDTO oCategoriaDTO = null;
		
		try {
			oCategoriaDTO = this.pesquisarDescricao(categoriaDTO.getDesCategoria());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oCategoriaDTO)) {
			if (categoriaDTO.getCodCategoria() == 0) {
				throw new ValidationException(Constants.CATEGORIA_JA_CADASTRADA);
			} else {
				if (categoriaDTO.getCodCategoria() != oCategoriaDTO.getCodCategoria()) {
					throw new ValidationException(Constants.CATEGORIA_JA_CADASTRADA);
				}
			}
		}
	}
	
	public void gravar(CategoriaDTO categoriaDTO) throws ValidationException, Exception {
		this.validarEntrada(categoriaDTO);
		this.categoriaRepository.save( new CategoriaEntity(categoriaDTO) );
	}
	
	public void deletar(int codCategoria) throws ValidationException, Exception {
		this.pesquisarCodigo(codCategoria); // Caso não encontre levanta uma ValidationException
		
		// Mais a frente verificar se a categória está relacionada a peça e ou subcategorias
		
		try {
			this.categoriaRepository.deleteById(codCategoria);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public CategoriaDTO pesquisarCodigo(int codCategoria) throws ValidationException, Exception {
		try {
			return new CategoriaDTO(this.categoriaRepository.findById(codCategoria).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.CATEGORIA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public CategoriaDTO pesquisarDescricao(String desCategoria) throws ValidationException, Exception {
		try {
			return new CategoriaDTO(this.categoriaRepository.pesquisarDescricao(desCategoria).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.CATEGORIA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<CategoriaDTO> buscarDescricao(String desCategoria) throws ValidationException, Exception {
		try {
			return CategoriaDTO.toList(this.categoriaRepository.buscarDescricao(desCategoria));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
