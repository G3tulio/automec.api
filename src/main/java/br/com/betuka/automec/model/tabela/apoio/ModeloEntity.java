package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.ModeloDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelo")
public class ModeloEntity implements Serializable {

	private static final long serialVersionUID = 4233772585869160232L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_modelo")
	private int codModelo;
	
	@Column(name = "des_modelo", nullable = false, length = 100, unique = true)
	private String desModelo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "cod_marca", unique = false)
	private MarcaEntity marcaEntity;
	
	public ModeloEntity() {
		super();
	}

	public ModeloEntity(int codModelo, String desModelo, MarcaEntity marcaEntity) {
		super();
		this.codModelo = codModelo;
		this.desModelo = desModelo;
		this.marcaEntity = marcaEntity;
	}
	
	public ModeloEntity(ModeloDTO modeloDTO) {
		super();
		BeanUtils.copyProperties(modeloDTO, this);
		this.marcaEntity = new MarcaEntity(modeloDTO.getMarca());
	}

	public int getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(int codModelo) {
		this.codModelo = codModelo;
	}

	public String getDesModelo() {
		return desModelo;
	}

	public void setDesModelo(String desModelo) {
		this.desModelo = desModelo;
	}
	
	public MarcaEntity getMarca() {
		return marcaEntity;
	}
	
	public void setMarca(MarcaEntity marcaEntity) {
		this.marcaEntity = marcaEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codModelo, desModelo, marcaEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloEntity other = (ModeloEntity) obj;
		return Objects.equals(codModelo, other.codModelo) && 
			   Objects.equals(desModelo, other.desModelo) &&
			   Objects.equals(marcaEntity, other.marcaEntity);
	}

	@Override
	public String toString() {
		return "ModeloEntity [codModelo=" + codModelo + ", desModelo=" + desModelo + ", marca=" + marcaEntity.toString() + "]";
	}
}
