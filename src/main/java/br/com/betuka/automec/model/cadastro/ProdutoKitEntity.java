package br.com.betuka.automec.model.cadastro;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.cadastro.ProdutoKitDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_kit")
public class ProdutoKitEntity implements Serializable {
	
	private static final long serialVersionUID = 6011937884178747485L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_produto_kit")
	private int codProdutoKit;
	
	@Column(name = "cod_kit", nullable = false, length = 11, unique = false)
	private int codKit;
	
	@Column(name = "cod_produto", nullable = false, length = 11, unique = false)
	private int codProduto;
	
	@Column(name = "qtd_produto", nullable = false, length = 11, unique = false)
	private int qtdProduto;
	
	public ProdutoKitEntity() {
		super();
	}

	public ProdutoKitEntity(int codProdutoKit, int codKit, int codProduto, int qtdProduto) {
		super();
		this.codProdutoKit = codProdutoKit;
		this.codKit = codKit;
		this.codProduto = codProduto;
		this.qtdProduto = qtdProduto;
	}
	
	public ProdutoKitEntity(ProdutoKitDTO produtoKitDTO) {
		super();
		BeanUtils.copyProperties(produtoKitDTO, this);
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

	@Override
	public int hashCode() {
		return Objects.hash(codProdutoKit, codKit, codProduto, qtdProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoKitEntity other = (ProdutoKitEntity) obj;
		return Objects.equals(codProdutoKit, other.codProdutoKit) && 
			   Objects.equals(codKit, other.codKit) && 
			   Objects.equals(codProduto, other.codProduto) && 
			   qtdProduto == other.qtdProduto;
	}

	@Override
	public String toString() {
		return "ProdutoKitEntity [codProdutoKit=" + codProdutoKit + 
				", kit=" + codKit + 
				", produto=" + codProduto + 
				", qtdProduto=" + qtdProduto + "]";
	}
}
