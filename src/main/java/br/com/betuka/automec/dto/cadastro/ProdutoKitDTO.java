package br.com.betuka.automec.dto.cadastro;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.cadastro.ProdutoKitEntity;

public class ProdutoKitDTO {
	
	private int codProdutoKit;
	
	private int codKit;
	
	private int codProduto;
	
	private int qtdProduto;
	
	public ProdutoKitDTO() {
		super();
	}

	public ProdutoKitDTO(int codProdutoKit, int codKit, int codProduto, int qtdProduto) {
		super();
		this.codProdutoKit = codProdutoKit;
		this.codKit = codKit;
		this.codProduto = codProduto;
		this.qtdProduto = qtdProduto;
	}
	
	public ProdutoKitDTO(ProdutoKitEntity produtoKitEntity) {
		super();
		BeanUtils.copyProperties(produtoKitEntity, this);
	}
	
	public static List<ProdutoKitDTO> toList(List<ProdutoKitEntity> lista) {
		return lista.stream().map(ProdutoKitDTO::new).toList();
	}
	
	public int getCodProdutoKit() {
		return codProdutoKit;
	}

	public void setCodProdutoKit(int codProdutoKit) {
		this.codProdutoKit = codProdutoKit;
	}
	
	public int getCodKit() {
		return codKit;
	}

	public void setCodKit(int codKit) {
		this.codKit = codKit;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
}
