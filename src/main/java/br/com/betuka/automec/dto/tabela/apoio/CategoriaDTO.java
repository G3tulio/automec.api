package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.CategoriaEntity;

public class CategoriaDTO {
	
	private int codCategoria;
	private String desCategoria;
	
	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(int codCategoria, String desCategoria) {
		super();
		this.codCategoria = codCategoria;
		this.desCategoria = desCategoria;
	}
	
	public CategoriaDTO(CategoriaEntity CategoriaEntity) {
		super();
		BeanUtils.copyProperties(CategoriaEntity, this);
	}
	
	public static List<CategoriaDTO> toList(List<CategoriaEntity> lista) {
		return lista.stream().map(CategoriaDTO::new).toList();
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getDesCategoria() {
		return desCategoria;
	}

	public void setDesCategoria(String desCategoria) {
		this.desCategoria = desCategoria;
	}
}
