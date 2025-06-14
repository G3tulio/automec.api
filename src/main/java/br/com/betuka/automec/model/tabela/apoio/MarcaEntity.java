package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.MarcaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class MarcaEntity implements Serializable {
	
	private static final long serialVersionUID = 9173357058290012906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_marca")
	private int codMarca;
	
	@Column(name = "des_marca", nullable = false, length = 100, unique = true)
	private String desMarca;
	
	public MarcaEntity() {
		super();
	}

	public MarcaEntity(int codMarca, String desMarca) {
		super();
		this.codMarca = codMarca;
		this.desMarca = desMarca;
	}
	
	public MarcaEntity(MarcaDTO marcaDTO) {
		super();
		BeanUtils.copyProperties(marcaDTO, this);
	}
	
	public int getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(int codMarca) {
		this.codMarca = codMarca;
	}

	public String getDesMarca() {
		return desMarca;
	}

	public void setDesMarca(String desMarca) {
		this.desMarca = desMarca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codMarca, desMarca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcaEntity other = (MarcaEntity) obj;
		return Objects.equals(codMarca, other.codMarca) && 
			   Objects.equals(desMarca, other.desMarca);
	}

	@Override
	public String toString() {
		return "MarcaEntity [codMarca=" + codMarca + ", desMarca=" + desMarca + "]";
	}
}
