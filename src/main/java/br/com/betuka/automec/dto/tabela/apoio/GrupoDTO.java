package br.com.betuka.automec.dto.TabApoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabApoio.GrupoEntity;

public class GrupoDTO {

	private int codGrupo;
	private String desGrupo;
	
	public GrupoDTO() {
		super();
	}

	public GrupoDTO(int codGrupo, String desGrupo) {
		super();
		this.codGrupo = codGrupo;
		this.desGrupo = desGrupo;
	}
	
	public GrupoDTO(GrupoEntity grupoEntiry) {
		super();
		BeanUtils.copyProperties(grupoEntiry, this);
	}
	
	public static List<GrupoDTO> toList(List<GrupoEntity> lista) {
		return lista.stream().map(GrupoDTO::new).toList();
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
}
