package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.ComponenteEntity;

public class ComponenteDTO {

	private int codComponente;
	private String desComponente;
	private SistemaDTO sistemaDTO;
	
	public ComponenteDTO() {
		super();
	}

	public ComponenteDTO(int codComponente, String desComponente, SistemaDTO sistemaDTO) {
		super();
		this.codComponente = codComponente;
		this.desComponente = desComponente;
		this.sistemaDTO = sistemaDTO;
	}
	
	public ComponenteDTO(ComponenteEntity componenteEntity) {
		BeanUtils.copyProperties(componenteEntity, this);
		this.sistemaDTO = new SistemaDTO(componenteEntity.getSistema());
	}
	
	public static List<ComponenteDTO> toList(List<ComponenteEntity> lista) {
		return lista.stream().map(ComponenteDTO::new).toList();
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
	
	public SistemaDTO getSistema() {
		return sistemaDTO;
	}
	
	public void setSistema(SistemaDTO sistemaDTO) {
		this.sistemaDTO = sistemaDTO;
	}
}
