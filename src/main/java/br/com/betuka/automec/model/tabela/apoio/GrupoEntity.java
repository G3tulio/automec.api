package br.com.betuka.automec.model.tabApoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.TabApoio.GrupoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo")
public class GrupoEntity implements Serializable {

	private static final long serialVersionUID = -5591957355630045827L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_grupo")
	private int codGrupo;
	
	@Column(name = "des_grupo", nullable = false, length = 100, unique = true)
	private String desGrupo;
	
	public GrupoEntity() {
		super();
	}

	public GrupoEntity(int codGrupo, String desGrupo) {
		super();
		this.codGrupo = codGrupo;
		this.desGrupo = desGrupo;
	}
	
	public GrupoEntity(GrupoDTO grupoDTO) {
		super();
		BeanUtils.copyProperties(grupoDTO, this);
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getDesGrupo() {
		return desGrupo;
	}

	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codGrupo, desGrupo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoEntity other = (GrupoEntity) obj;
		return codGrupo == other.codGrupo && Objects.equals(desGrupo, other.desGrupo);
	}

	@Override
	public String toString() {
		return "GrupoEntiry [codGrupo=" + codGrupo + ", desGrupo=" + desGrupo + "]";
	}
}
