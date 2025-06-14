package br.com.betuka.automec.repository.tabela.acesso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.acesso.PerfilEntity;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Integer> {
	
	@Query("select p from PerfilEntity p where p.desPerfil = :desPerfil")
	Optional<PerfilEntity> pesquisarDescricao(@Param("desPerfil") String desPerfil);
	
	@Query("select p from PerfilEntity p where p.desPerfil like concat('%', :desPerfil, '%')")
	List<PerfilEntity> buscarDescricao(@Param("desPerfil") String desPerfil);
	
	@Query("select (count(u) > 0) as existe from UsuarioEntity u where u.perfil.codPerfil = :codPerfil")
	boolean existeUsuarioPerfil(@Param("codPerfil") int codPerfil);
	
}
