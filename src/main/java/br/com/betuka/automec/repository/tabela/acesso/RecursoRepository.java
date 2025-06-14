package br.com.betuka.automec.repository.tabela.acesso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.acesso.RecursoEntity;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Integer> {
	
	@Query("select r from RecursoEntity r where r.desRecurso = :desRecurso")
	Optional<RecursoEntity> pesquisarDescricao(@Param("desRecurso") String desRecurso);
	
	@Query("select r from RecursoEntity r where r.desRecurso like concat('%', :desRecurso, '%')")
	List<RecursoEntity> buscarDescricao(@Param("desRecurso") String desRecurso);
	
}
