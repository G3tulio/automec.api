package br.com.betuka.automec.dto.tabela.apoio;

public class VWModelosMarcaDTO {
	
	private int codModelo;
	private String desModelo;
	
	public VWModelosMarcaDTO() {
		super();
	}
	
	public VWModelosMarcaDTO(
			int codModelo, 
			String desModelo) {
		super();
		this.codModelo = codModelo;
		this.desModelo = desModelo;
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
}
