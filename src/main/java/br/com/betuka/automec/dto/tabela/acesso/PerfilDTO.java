package br.com.betuka.automec.dto.tabAcesso;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabAcesso.PerfilEntity;

public class PerfilDTO {

	private int codPerfil;
	private String desPerfil;
	
	public PerfilDTO() {
		super();		
	}
	
	public PerfilDTO(int codPerfil, String desPerfil) {
		super();
		this.codPerfil = codPerfil;
		this.desPerfil = desPerfil;
	}
	
	public PerfilDTO(PerfilEntity perfilEntity) {
		super();
	    BeanUtils.copyProperties(perfilEntity, this);
	}
	
	public static List<PerfilDTO> toList(List<PerfilEntity> lista) {
		return lista.stream().map(PerfilDTO::new).toList();
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
}
