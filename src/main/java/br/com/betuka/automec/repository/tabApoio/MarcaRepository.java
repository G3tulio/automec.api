package br.com.betuka.automec.repository.tabApoio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabApoio.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
	
	@Query("SELECT m FROM MarcaEntity m WHERE m.desMarca = :desMarcar")
	Optional<MarcaEntity> pesquisarDescricao(@Param("desMarcar") String desMarca);
	
}
