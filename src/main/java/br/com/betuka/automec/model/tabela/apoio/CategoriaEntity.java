package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.CategoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class CategoriaEntity implements Serializable {
	
	private static final long serialVersionUID = 9173357058290012906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_categoria")
	private int codCategoria;
	
	@Column(name = "des_categoria", nullable = false, length = 100, unique = true)
	private String desCategoria;
	
	public CategoriaEntity() {
		super();
	}

	public CategoriaEntity(int codCategoria, String desCategoria) {
		super();
		this.codCategoria = codCategoria;
		this.desCategoria = desCategoria;
	}
	
	public CategoriaEntity(CategoriaDTO categoriaDTO) {
		super();
		BeanUtils.copyProperties(categoriaDTO, this);
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getDesCategoria() {
		return desCategoria;
	}

	public void setDesCategoria(String desCategoria) {
		this.desCategoria = desCategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCategoria, desCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaEntity other = (CategoriaEntity) obj;
		return Objects.equals(codCategoria, other.codCategoria) && 
			   Objects.equals(desCategoria, other.desCategoria);
	}

	@Override
	public String toString() {
		return "CategoriaEntity [codCategoria=" + codCategoria + ", desCategoria=" + desCategoria + "]";
	}
}
