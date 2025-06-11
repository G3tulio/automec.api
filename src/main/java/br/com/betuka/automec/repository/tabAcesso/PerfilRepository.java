package br.com.betuka.automec.repository.tabAcesso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabAcesso.PerfilEntity;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Integer> {
	
	@Query("select p from PerfilEntity p where p.desPerfil = :desPerfil")
	Optional<PerfilEntity> pesquisarDescricao(@Param("desPerfil") String desPerfil);
	
	@Query("select p from PerfilEntity p where p.desPerfil like concat('%', :desPerfil, '%')")
	List<PerfilEntity> buscarDescricao(@Param("desPerfil") String desPerfil);
	
	@Query("select (count(u) > 0) as existe from UsuarioEntity u where u.perfil.codPerfil = :codPerfil")
	boolean existePerfilUsuario(@Param("codPerfil") int codPerfil);
	
}
