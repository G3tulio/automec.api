package br.com.betuka.automec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.FabricanteEntity;

public interface FabricanteRepository extends JpaRepository<FabricanteEntity, Integer> {

	@Query("select f from FabricanteEntity f where f.desFabricante like concat('%', :desFabricante, '%')")
	List<FabricanteEntity> pesquisarDescricao(@Param("desFabricante") String desFabricante);

	@Query("select f from FabricanteEntity f where f.desFabricante = :desFabricante")
	Optional<FabricanteEntity> pesquisarPorDescricao(@Param("desFabricante") String desFabricante);
		
}
