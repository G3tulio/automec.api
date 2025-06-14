package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.MecanicoEntity;

public class MecanicoDTO {
	
	private int codMecanico;
	private String nomMecanico;
	private String nroCelular;
	
	public MecanicoDTO() {
		super();
	}

	public MecanicoDTO(int codMecanico, String nomMecanico, String nroCelular) {
		super();
		this.codMecanico = codMecanico;
		this.nomMecanico = nomMecanico;
		this.nroCelular  = nroCelular;
	}
	
	public MecanicoDTO(MecanicoEntity mecanicoEntity) {
		super();
		BeanUtils.copyProperties(mecanicoEntity, this);
	}
	
	public static List<MecanicoDTO> toList(List<MecanicoEntity> lista) {
		return lista.stream().map(MecanicoDTO::new).toList();
	}

	public int getCodMecanico() {
		return codMecanico;
	}

	public void setCodMecanico(int codMecanico) {
		this.codMecanico = codMecanico;
	}

	public String getNomMecanico() {
		return nomMecanico;
	}

	public void setNomMecanico(String nomMecanico) {
		this.nomMecanico = nomMecanico;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}
}
