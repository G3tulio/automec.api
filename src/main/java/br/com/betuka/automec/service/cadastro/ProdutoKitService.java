package br.com.betuka.automec.service.cadastro;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.ProdutoKitDTO;
import br.com.betuka.automec.dto.cadastro.VWProdutoKitDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.ProdutoKitEntity;
import br.com.betuka.automec.repository.cadastro.ProdutoKitRepository;
import br.com.betuka.automec.service.tabela.apoio.KitService;

@Service
public class ProdutoKitService {

	@Autowired
	private ProdutoKitRepository produtoKitRepository;
	
	@Autowired
	private KitService kitService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public List<ProdutoKitDTO> listar() {
		return ProdutoKitDTO.toList(this.produtoKitRepository.findAll());
	}
	
	private void validarEntrada(ProdutoKitDTO produtoKitDTO) throws ValidationException, Exception {
		if (produtoKitDTO.getCodProdutoKit() != 0) {
			this.pesquisarCodigo(produtoKitDTO.getCodProdutoKit());
		}
		
		if (produtoKitDTO.getCodKit() == 0) {
			throw new ValidationException(Constants.KIT_CODIGO_N_INFORMADO);
		}
		
		if (produtoKitDTO.getCodProduto() == 0) {
			throw new ValidationException(Constants.PRODUTO_CODIGO_N_INFORMADO);
		}
		
		this.kitService.pesquisarCodigo(produtoKitDTO.getCodKit());
		
		this.produtoService.pesquisarCodigo(produtoKitDTO.getCodProduto());
		
		if (produtoKitDTO.getQtdProduto() < 1) {
			throw new ValidationException(Constants.PRODUTO_KIT_QTDE_ZERO);
		}
		
		ProdutoKitDTO oProdutoKitDTO = null;		
		try {
			oProdutoKitDTO = this.pesquisarItem(produtoKitDTO.getCodKit(), produtoKitDTO.getCodProduto());
		} catch (ValidationException e) {
			// Não tem exception, neste caso não encontrou
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oProdutoKitDTO)) {
			if (produtoKitDTO.getCodProdutoKit() == 0) {
				throw new ValidationException(Constants.PRODUTO_KIT_ITEM_JA_CADASTRADO);
			} else {
				if (produtoKitDTO.getCodProdutoKit() != oProdutoKitDTO.getCodProdutoKit()) {
					throw new ValidationException(Constants.PRODUTO_KIT_ITEM_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(ProdutoKitDTO produtoKitDTO) throws ValidationException, Exception {
		this.validarEntrada(produtoKitDTO);
		this.produtoKitRepository.save(new ProdutoKitEntity(produtoKitDTO));
	}
	
	public void deletar(int codProdutoKit) throws ValidationException, Exception {
		this.pesquisarCodigo(codProdutoKit);
		
		// Mais a frente verificar se o item do kit tem relação com a entidade serviço [ FK ]
		
		try {
			this.produtoKitRepository.deleteById(codProdutoKit);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ProdutoKitDTO pesquisarCodigo(int codProdutoKit) throws ValidationException, Exception {
		try {
			return new ProdutoKitDTO(this.produtoKitRepository.findById(codProdutoKit).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PRODUTO_KIT_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ProdutoKitDTO pesquisarItem(int codKit, int codProduto) throws ValidationException, Exception {
		try {
			return new ProdutoKitDTO(this.produtoKitRepository.pesquisarItem(codKit, codProduto).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.PRODUTO_KIT_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<VWProdutoKitDTO> listarProdutosKit(int codKit) throws Exception {
		try {
			return this.produtoKitRepository.listarProdutosKit(codKit).get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
