package br.com.betuka.automec.service.cadastro;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.ProdutoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.ProdutoEntity;
import br.com.betuka.automec.repository.cadastro.ProdutoRepository;
import br.com.betuka.automec.service.tabela.apoio.ComponenteService;
import br.com.betuka.automec.util.enums.SituacaoEnum;

@Service
public class ProdutoService {
	
	List<String> undMedidaValidas = Arrays.asList("UN", "CX", "LT");
	List<String> IndcRevisaoValidos = Arrays.asList("S", "N");

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FabricanteService fabricanteService;
	
	@Autowired
	private ComponenteService componenteService;
	
	public List<ProdutoDTO> listar() {
		return ProdutoDTO.toList(this.produtoRepository.findAll());
	}
	
	private void validarEntrada(ProdutoDTO produtoDTO) throws ValidationException, Exception {
		if (produtoDTO.getCodProduto() != 0) {
			this.pesquisarCodigo(produtoDTO.getCodProduto());
		}
		
		if (produtoDTO.getNomProduto().isBlank()) {
			throw new ValidationException(Constants.PRODUTO_NOME_OBRIGATORIO);
		}
		
		if ( (produtoDTO.getUndMedida().isBlank()) || (! undMedidaValidas.contains(produtoDTO.getUndMedida())) ) {
			throw new ValidationException(Constants.PRODUTO_UND_MEDIDA_INEXISTENTE);
		}
		
		if ( (produtoDTO.getIndRevisao().isBlank()) || (! IndcRevisaoValidos.contains(produtoDTO.getIndRevisao())) ) {
			throw new ValidationException(Constants.PRODUTO_INDC_REVISAO_INEXISTENTE);
		}
		
		this.fabricanteService.pesquisarCodigo(produtoDTO.getFabricanteDTO().getCodFabricante());
		
		this.componenteService.pesquisarCodigo(produtoDTO.getComponenteDTO().getCodComponente());
		
		if (! EnumSet.of(SituacaoEnum.A, SituacaoEnum.I).contains(produtoDTO.getIndSituacao())) {
			throw new ValidationException(Constants.PRODUTO_SITUACAO_INEXISTENTE);
		}
		
		ProdutoDTO oProdutoDTO = null;
		
		try {
			oProdutoDTO = this.pesquisarNome(produtoDTO.getNomProduto());
		} catch (ValidationException e) {
			// Não tem exception, neste caso
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oProdutoDTO)) {
			if (produtoDTO.getCodProduto() == 0) {
				throw new ValidationException(Constants.PRODUTO_JA_CADASTRADO);
			} else {
				if (produtoDTO.getCodProduto() != oProdutoDTO.getCodProduto()) {
					throw new ValidationException(Constants.PRODUTO_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(ProdutoDTO produtoDTO) throws ValidationException, Exception {
		this.validarEntrada(produtoDTO);
		this.produtoRepository.save(new ProdutoEntity(produtoDTO));
	}
	
	public void deletar(int codProduto) throws ValidationException, Exception {
		this.pesquisarCodigo(codProduto);
		
		// Mais a frente verificar se o produto tem relação com a entidade serviço e ou item de compra [ FK ]
		
		try {
			this.produtoRepository.deleteById(codProduto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ProdutoDTO pesquisarCodigo(int codProduto) throws ValidationException, Exception {
		try {
			return new ProdutoDTO(this.produtoRepository.findById(codProduto).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PRODUTO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ProdutoDTO pesquisarNome(String nomProduto) throws Exception {
		try {
			return new ProdutoDTO(this.produtoRepository.pesquisarNome(nomProduto).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PRODUTO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<ProdutoDTO> buscarNome(String nomProduto) throws Exception {
		try {
			return ProdutoDTO.toList(this.produtoRepository.buscarNome(nomProduto));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
