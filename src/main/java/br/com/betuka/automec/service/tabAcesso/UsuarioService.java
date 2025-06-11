package br.com.betuka.automec.service.tabAcesso;

import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabAcesso.AutenticaDTO;
import br.com.betuka.automec.dto.tabAcesso.UsuarioDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabAcesso.UsuarioEntity;
import br.com.betuka.automec.repository.tabAcesso.UsuarioRepository;
import br.com.betuka.automec.util.ValidEmail;
import br.com.betuka.automec.util.enums.SituacaoEnum;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	public List<UsuarioDTO> listar() {
		return UsuarioDTO.toList(this.usuarioRepository.findAll());
	}
	
	private void validarEntrada(UsuarioDTO usuarioDTO) throws ValidationException, Exception {		
		if (usuarioDTO.getCodUsuario() != 0) {
			this.pesquisarCodigo(usuarioDTO.getCodUsuario());
		}
		
		if (usuarioDTO.getNome().isBlank()) {
			throw new ValidationException(Constants.USUARIO_NOME_OBRIGATORIO);
		}
		
		if (usuarioDTO.getEmail().isBlank()) {
			throw new ValidationException(Constants.USUARIO_EMAIL_OBRIGATORIO);
		}
		
		if (! ValidEmail.isValidEmailAddressRegex(usuarioDTO.getEmail())) {
			throw new ValidationException(Constants.USUARIO_EMAIL_INCONSISTENTE);
		}
		
		if (usuarioDTO.getLogin().isBlank()) {
			throw new ValidationException(Constants.USUARIO_LOGIN_OBRIGATORIO);
		}
		
		if (usuarioDTO.getLogin().length() < 6) {
			throw new ValidationException(Constants.USUARIO_LOGIN_TAMANHO_MINIMO);
		}
		
		UsuarioDTO oUsuarioDTO = null;
		
		try {
			oUsuarioDTO = this.pesquisarNome(usuarioDTO.getNome());
		} catch (ValidationException e) {
			// Não encontrou o nome do usuário
		}
		
		if (Objects.nonNull(oUsuarioDTO)) {
			if (usuarioDTO.getCodUsuario() == 0) {
				throw new ValidationException(Constants.USUARIO_NOME_CADASTRADO);
			} else {
				if  (usuarioDTO.getCodUsuario() != oUsuarioDTO.getCodUsuario()) {
					throw new ValidationException(Constants.USUARIO_NOME_CADASTRADO);
				} 
			}
		}
		
		oUsuarioDTO = null;
		
		try {
			oUsuarioDTO = this.pesquisarLogin(usuarioDTO.getLogin());
		} catch (ValidationException e) {
			// Não encontrou o login do usuário
		}
		
		if (Objects.nonNull(oUsuarioDTO)) {
			if (usuarioDTO.getCodUsuario() == 0) {
				throw new ValidationException(Constants.USUARIO_LOGIN_CADASTRADO);
			} else {
				if (usuarioDTO.getCodUsuario() != oUsuarioDTO.getCodUsuario()) {
					throw new ValidationException(Constants.USUARIO_LOGIN_CADASTRADO);
				} 
			}
		}
		
		if (usuarioDTO.getSenha().isEmpty()) {
			throw new ValidationException(Constants.USUARIO_SENHA_OBRIGATORIA);
		}
		
		if (usuarioDTO.getSenha().length() < 6) {
			throw new ValidationException(Constants.USUARIO_SENHA_TAMANHO_MINIMO);
		}
				
		if (! EnumSet.of(SituacaoEnum.A, SituacaoEnum.I).contains(usuarioDTO.getSituacao())) {
			throw new ValidationException(Constants.USUARIO_SITUACAO_INEXISTENTE);
		}
		
		this.perfilService.pesquisarCodigo(usuarioDTO.getPerfil().getCodPerfil());
	}
	
	public void gravar(UsuarioDTO usuarioDTO) throws ValidationException, Exception {
		this.validarEntrada(usuarioDTO);
		this.usuarioRepository.save(new UsuarioEntity(usuarioDTO));
	}
	
	public void deletar(int codUsuario) throws ValidationException, Exception {
		this.pesquisarCodigo(codUsuario); // Caso não encontre, levanta uma ValidationException
		
		// Mais a frente verificar se o usuário está sendo utilizado em outra tabela ( FK )
		
		try {
			this.usuarioRepository.deleteById(codUsuario);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public UsuarioDTO pesquisarCodigo(int codUsuario) throws ValidationException, Exception {
		try {
			return new UsuarioDTO(this.usuarioRepository.findById(codUsuario).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.USUARIO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public UsuarioDTO pesquisarNome(String nomUsuario) throws ValidationException, Exception {
		try {
			return new UsuarioDTO(this.usuarioRepository.pesquisarNome(nomUsuario).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.USUARIO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public UsuarioDTO pesquisarLogin(String login) throws ValidationException, Exception {
		try {
			return new UsuarioDTO(this.usuarioRepository.pesquisarLogin(login).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.USUARIO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public AutenticaDTO autenticar(String login, String senha) throws ValidationException, Exception {
		if (login.isBlank()) {
			throw new ValidationException(Constants.USUARIO_LOGIN_OBRIGATORIO);
		}
		
		if (senha.isBlank()) {
			throw new ValidationException(Constants.USUARIO_SENHA_OBRIGATORIA);
		}
		
		UsuarioDTO usuarioDTO = this.pesquisarLogin(login); // Se não for localizado, levanta uma ValidationException
		
		if (! senha.equals(usuarioDTO.getSenha())) {
			throw new ValidationException(Constants.AUTENTICACAO_LOGIN_INEXISTEN);
		}
		
		if (! usuarioDTO.getSituacao().equals(SituacaoEnum.A)) {
			throw new ValidationException(Constants.AUTENTICACAO_LOGIN_NAO_ATIVO);
		}
		
		return new AutenticaDTO(usuarioDTO.getCodUsuario(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getPerfil());
	}	
}
