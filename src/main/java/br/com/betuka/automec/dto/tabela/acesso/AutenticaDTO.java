package br.com.betuka.automec.dto.tabela.acesso;

public class AutenticaDTO {

	private int codUsuario;
	private String nome;
	private String email;
	private PerfilDTO perfil;
	
	public AutenticaDTO() {
		super();
	}

	public AutenticaDTO(int codUsuario, String nome, String email, PerfilDTO perfil) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
}
