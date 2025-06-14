package br.com.betuka.automec.dto.TabApoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabApoio.ModeloEntity;

public class ModeloDTO {

	private int codModelo;
	private String desModelo;
	private MarcaDTO marca;
	
	public ModeloDTO() {
		super();
	}

	public ModeloDTO(int codModelo, String desModelo, MarcaDTO marca) {
		super();
		this.codModelo = codModelo;
		this.desModelo = desModelo;
		this.marca = marca;
	}
	
	public ModeloDTO(ModeloEntity modeloEntity) {
		BeanUtils.copyProperties(modeloEntity, this);
		this.marca = new MarcaDTO(modeloEntity.getMarca());
	}
	
	public static List<ModeloDTO> toList(List<ModeloEntity> lista) {
		return lista.stream().map(ModeloDTO::new).toList();
	}

	public int getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(int codModelo) {
		this.codModelo = codModelo;
	}

	public String getDesModelo() {
		return desModelo;
	}

	public void setDesModelo(String desModelo) {
		this.desModelo = desModelo;
	}
	
	public MarcaDTO getMarca() {
		return marca;
	}
	
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
}
