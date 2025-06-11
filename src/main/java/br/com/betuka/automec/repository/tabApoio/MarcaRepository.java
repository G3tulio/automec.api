package br.com.betuka.automec.repository.tabApoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabApoio.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
	
	@Query("select m from MarcaEntity m where m.desMarca = :desMarcar")
	Optional<MarcaEntity> pesquisarDescricao(@Param("desMarcar") String desMarca);
	
	@Query("select m from MarcaEntity m where m.desMarca like concat('%', :desMarcar, '%')")
	List<MarcaEntity> buscarDescricao(@Param("desMarcar") String desMarca);
	
	@Query("select (count(*) > 0) as existe from ModeloEntity m where m.marca.codMarca = :codMarca")
	boolean existeModeloMarca(@Param("codMarca") int codMarca);
	
}
