package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.SistemaEntity;

public class SistemaDTO {
	
	private int codSistema;
	private String desSistema;
	
	public SistemaDTO() {
		super();
	}

	public SistemaDTO(int codSistema, String desSistema) {
		super();
		this.codSistema = codSistema;
		this.desSistema = desSistema;
	}
	
	public SistemaDTO(SistemaEntity SistemaEntity) {
		super();
		BeanUtils.copyProperties(SistemaEntity, this);
	}
	
	public static List<SistemaDTO> toList(List<SistemaEntity> lista) {
		return lista.stream().map(SistemaDTO::new).toList();
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
}
