package br.com.betuka.automec.dto.tabAcesso;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabAcesso.UsuarioEntity;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UsuarioDTO {

	private int codUsuario;
	
	private String nome;
	
	private String email;
	
	private String login;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao = SituacaoEnum.I;
	
	private PerfilDTO perfil;
	
	public UsuarioDTO() {
		super();		
	}
	
	public UsuarioDTO(
			int codUsuario, 
			String nome, 
			String email, 
			String login, 
			String senha, 
			SituacaoEnum situacao,
			PerfilDTO perfil) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.situacao = situacao;
		this.perfil = perfil;
	}
	
	public UsuarioDTO(UsuarioEntity usuarioEntity)	{
		super();
		BeanUtils.copyProperties(usuarioEntity, this);
		this.perfil = new PerfilDTO(usuarioEntity.getPerfil());
	}
	
	public static List<UsuarioDTO> toList(List<UsuarioEntity> lista) {
		return lista.stream().map(UsuarioDTO::new).toList();
	}
	
	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome.toUpperCase();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login.toLowerCase();
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	
	public boolean estaAtivo() {
		return this.situacao.equals(SituacaoEnum.A);
	}
}
