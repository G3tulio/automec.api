package br.com.betuka.automec.model.tabAcesso;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabAcesso.UsuarioDTO;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = -5602301777305558682L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_usuario")
	private int codUsuario;
	
	@Column(nullable = false, length = 100, unique = false)
	private String nome;
	
	@Column(nullable = false, length = 100, unique = false)
	private String email;
	
	@Column(nullable = false, length = 25, unique = true)
	private String login;
	
	@Column(nullable = false, length = 25, unique = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1, unique = false)
	private SituacaoEnum situacao = SituacaoEnum.I;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_perfil", nullable = false, unique = false)
	private PerfilEntity perfil;
	
	public UsuarioEntity() {
		super();
	}
	
	public UsuarioEntity(
			int codUsuario, 
			String nome, 
			String email, 
			String login, 
			String senha, 
			SituacaoEnum situacao,
			PerfilEntity perfil) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.situacao = situacao;
		this.perfil = perfil;
	}
	
	public UsuarioEntity(UsuarioDTO usuarioDTO) {
		super();
		BeanUtils.copyProperties(usuarioDTO, this);
		this.perfil = new PerfilEntity(usuarioDTO.getPerfil());
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
		return email;
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
	
	public PerfilEntity getPerfil () {
		return perfil;
	}

	public void setPerfil(PerfilEntity perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codUsuario, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(codUsuario, other.codUsuario)
				&& Objects.equals(nome, other.nome)
				&& Objects.equals(email, other.email)
				&& Objects.equals(login, other.login)
				&& Objects.equals(senha, other.senha)
				&& Objects.equals(situacao, other.situacao)
				&& Objects.equals(perfil, other.perfil);
	}

	@Override
	public String toString() {
		return "UsuarioEntity [codUsuario=" + codUsuario + 
				", nome=" + nome + 
				", email=" + email + 
				", login=" + login + 
				", senha=" + senha + 
				", situacao=" + situacao + 
				", perfil=" + perfil.toString() + "]";
	}
	
	public boolean estaAtivo() {
		return this.situacao.equals(SituacaoEnum.A);
	}
}
