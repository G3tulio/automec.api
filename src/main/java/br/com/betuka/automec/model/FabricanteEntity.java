package br.com.betuka.automec.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.FabricanteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fabricante")
public class FabricanteEntity implements Serializable {

	private static final long serialVersionUID = 1242288905292321508L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_fabricante")
	private int codFabricante;
	
	@Column(name = "des_fabricante", nullable = false, length = 100, unique = true)
	private String desFabricante;
	
	public FabricanteEntity() {
		super();
	}

	public FabricanteEntity(int codFabricante, String desFabricante) {
		super();
		this.codFabricante = codFabricante;
		this.desFabricante = desFabricante;
	}
	
	public FabricanteEntity(FabricanteDTO fabricanteDTO) {
		super();
		BeanUtils.copyProperties(fabricanteDTO, this);
	}

	public int getCodFabricante() {
		return codFabricante;
	}

	public void setCodFabricante(int codFabricante) {
		this.codFabricante = codFabricante;
	}

	public String getDesFabricante() {
		return desFabricante;
	}

	public void setDesFabricante(String desFabricante) {
		this.desFabricante = desFabricante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codFabricante, desFabricante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FabricanteEntity other = (FabricanteEntity) obj;
		return codFabricante == other.codFabricante && Objects.equals(desFabricante, other.desFabricante);
	}

	@Override
	public String toString() {
		return "FabricanteEntity [codFabricante=" + codFabricante + ", desFabricante=" + desFabricante + "]";
	}
}
