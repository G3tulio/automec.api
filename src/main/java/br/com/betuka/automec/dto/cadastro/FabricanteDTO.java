package br.com.betuka.automec.dto.cadastro;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.cadastro.FabricanteEntity;

public class FabricanteDTO {
	
	private int codFabricante;
	
	private String desFabricante;
	
	public FabricanteDTO() {
		super();
	}

	public FabricanteDTO(int codFabricante, String desFabricante) {
		super();
		this.codFabricante = codFabricante;
		this.desFabricante = desFabricante;
	}
	
	public FabricanteDTO(FabricanteEntity fabricanteEntity) {
		super();
		BeanUtils.copyProperties(fabricanteEntity, this);
	}
	
	public static List<FabricanteDTO> toList(List<FabricanteEntity> lista) {
		return lista.stream().map(FabricanteDTO::new).toList();
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
}
