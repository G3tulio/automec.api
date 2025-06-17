package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.ModeloEntity;

public class ModeloDTO {

	private int codModelo;
	private String desModelo;
	private MarcaDTO marcaDTO;
	
	public ModeloDTO() {
		super();
	}

	public ModeloDTO(int codModelo, String desModelo, MarcaDTO marcaDTO) {
		super();
		this.codModelo = codModelo;
		this.desModelo = desModelo;
		this.marcaDTO = marcaDTO;
	}
	
	public ModeloDTO(ModeloEntity modeloEntity) {
		BeanUtils.copyProperties(modeloEntity, this);
		this.marcaDTO = new MarcaDTO(modeloEntity.getMarca());
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
		return marcaDTO;
	}
	
	public void setMarca(MarcaDTO marcaDTO) {
		this.marcaDTO = marcaDTO;
	}
}
