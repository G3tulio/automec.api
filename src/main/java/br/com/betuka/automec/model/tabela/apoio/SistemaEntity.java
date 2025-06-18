package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.SistemaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sistema")
public class SistemaEntity implements Serializable {
	
	private static final long serialVersionUID = 9173357058290012906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_sistema")
	private int codSistema;
	
	@Column(name = "des_sistema", nullable = false, length = 100, unique = true)
	private String desSistema;
	
	public SistemaEntity() {
		super();
	}

	public SistemaEntity(int codSistema, String desSistema) {
		super();
		this.codSistema = codSistema;
		this.desSistema = desSistema;
	}
	
	public SistemaEntity(SistemaDTO sistemaDTO) {
		super();
		BeanUtils.copyProperties(sistemaDTO, this);
	}
	
	public int getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(int codSistema) {
		this.codSistema = codSistema;
	}

	public String getDesSistema() {
		return desSistema;
	}

	public void setDesSistema(String desSistema) {
		this.desSistema = desSistema;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codSistema, desSistema);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SistemaEntity other = (SistemaEntity) obj;
		return Objects.equals(codSistema, other.codSistema) && 
			   Objects.equals(desSistema, other.desSistema);
	}

	@Override
	public String toString() {
		return "SistemaEntity [codSistema=" + codSistema + ", desSistema=" + desSistema + "]";
	}
}
