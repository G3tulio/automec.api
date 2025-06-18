package br.com.betuka.automec.dto.tabela.apoio;

public class VWComponentesSistemaDTO {
	
	private int codComponente;
	private String desComponente;
	
	public VWComponentesSistemaDTO() {
		super();
	}
	
	public VWComponentesSistemaDTO(
			int codComponente, 
			String desComponente) {
		super();
		this.codComponente = codComponente;
		this.desComponente = desComponente;
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
}
