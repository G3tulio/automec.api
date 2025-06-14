package br.com.betuka.automec.model.tabAcesso;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabAcesso.PerfilDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class PerfilEntity implements Serializable {

	private static final long serialVersionUID = -7143216122643985178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_perfil")
	private int codPerfil;

	@Column(name = "des_perfil", nullable = false, length = 100, unique = true)
	private String desPerfil;

	public PerfilEntity() {
		super();
	}

	public PerfilEntity(int codPerfil, String desPerfil) {
		super();
		this.codPerfil = codPerfil;
		this.desPerfil = desPerfil;
	}

	public PerfilEntity(PerfilDTO perfilDTO) {
		super();
		BeanUtils.copyProperties(perfilDTO, this);
	}

	public int getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(int codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getDesPerfil() {
		return desPerfil;
	}

	public void setDesPerfil(String desPerfil) {
		this.desPerfil = desPerfil;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codPerfil, desPerfil);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilEntity other = (PerfilEntity) obj;
		return Objects.equals(codPerfil, other.codPerfil) && Objects.equals(desPerfil, other.desPerfil);
	}

	@Override
	public String toString() {
		return "perfil [codPerfil=" + codPerfil + ", desPerfil=" + desPerfil + "]";
	}
}
