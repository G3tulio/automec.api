package br.com.betuka.automec.repository.tabela.acesso;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.acesso.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
	
	@Query("select u from UsuarioEntity u where u.login = :login")
	Optional<UsuarioEntity> pesquisarLogin(@Param("login") String login);
	
	@Query("select u from UsuarioEntity u where u.nome = :nome")
	Optional<UsuarioEntity> pesquisarNome(@Param("nome") String nome);
	
}
