package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.ComponenteDTO;
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
@Table(name = "componente")
public class ComponenteEntity implements Serializable {

	private static final long serialVersionUID = 4233772585869160232L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_componente")
	private int codComponente;
	
	@Column(name = "des_componente", nullable = false, length = 100, unique = true)
	private String desComponente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "cod_sistema", unique = false)
	private SistemaEntity sistemaEntity;
	
	public ComponenteEntity() {
		super();
	}

	public ComponenteEntity(int codComponente, String desComponente, SistemaEntity sistemaEntity) {
		super();
		this.codComponente = codComponente;
		this.desComponente = desComponente;
		this.sistemaEntity = sistemaEntity;
	}
	
	public ComponenteEntity(ComponenteDTO componenteDTO) {
		super();
		BeanUtils.copyProperties(componenteDTO, this);
		this.sistemaEntity = new SistemaEntity(componenteDTO.getSistema());
	}

	public int getCodComponente() {
		return codComponente;
	}

	public void setCodComponente(int codComponente) {
		this.codComponente = codComponente;
	}

	public String getDesComponente() {
		return desComponente;
	}

	public void setDesComponente(String desComponente) {
		this.desComponente = desComponente;
	}
	
	public SistemaEntity getSistema() {
		return sistemaEntity;
	}
	
	public void setSistema(SistemaEntity sistemaEntity) {
		this.sistemaEntity = sistemaEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codComponente, desComponente, sistemaEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponenteEntity other = (ComponenteEntity) obj;
		return Objects.equals(codComponente, other.codComponente) && 
			   Objects.equals(desComponente, other.desComponente) &&
			   Objects.equals(sistemaEntity, other.sistemaEntity);
	}

	@Override
	public String toString() {
		return "ComponenteEntity [codComponente=" + codComponente + ", desComponente=" + desComponente + ", sistema=" + sistemaEntity.toString() + "]";
	}
}
