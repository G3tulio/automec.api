package br.com.betuka.automec.dto.TabApoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabApoio.MarcaEntity;

public class MarcaDTO {
	
	private int codMarca;
	private String desMarca;
	
	public MarcaDTO() {
		super();
	}

	public MarcaDTO(int codMarca, String desMarca) {
		super();
		this.codMarca = codMarca;
		this.desMarca = desMarca;
	}
	
	public MarcaDTO(MarcaEntity marcaEntity) {
		super();
		BeanUtils.copyProperties(marcaEntity, this);
	}
	
	public static List<MarcaDTO> toList(List<MarcaEntity> lista) {
		return lista.stream().map(MarcaDTO::new).toList();
	}

	public int getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(int codMarca) {
		this.codMarca = codMarca;
	}

	public String getDesMarca() {
		return desMarca;
	}

	public void setDesMarca(String desMarca) {
		this.desMarca = desMarca;
	}
}
