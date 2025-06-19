package br.com.betuka.automec.repository.tabela.apoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.apoio.KitEntity;

public interface KitRepository extends JpaRepository<KitEntity, Integer> {
	
	@Query("select m from KitEntity m where m.desKit = :desKitr")
	Optional<KitEntity> pesquisarDescricao(@Param("desKitr") String desKit);
	
	@Query("select m from KitEntity m where m.desKit like concat('%', :desKitr, '%')")
	List<KitEntity> buscarDescricao(@Param("desKitr") String desKit);
	
//	@Query("select (count(*) > 0) as existe from ModeloEntity m where m.KitEntity.codKit = :codKit")
//	boolean existeModeloKit(@Param("codKit") int codKit);
	
}
