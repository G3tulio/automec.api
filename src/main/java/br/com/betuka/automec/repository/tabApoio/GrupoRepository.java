package br.com.betuka.automec.repository.tabApoio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabApoio.GrupoEntity;

public interface GrupoRepository extends JpaRepository<GrupoEntity, Integer> {

	@Query("select g from GrupoEntity g where g.desGrupo like concat('%', :desGrupo, '%')")
	List<GrupoEntity> pesquisarDescricao(@Param("desGrupo") String desGrupo);
}
