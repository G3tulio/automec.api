package br.com.betuka.automec.dto.cadastro;

public class VWProdutoKitDTO {
	
	private int codProduto;
	private int qtdProduto;
	
	public VWProdutoKitDTO() {
		super();
	}
	
	public VWProdutoKitDTO(
			int codProduto,
			int qtdProduto ) {
		super();
		this.codProduto = codProduto;
		this.qtdProduto = qtdProduto;
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
