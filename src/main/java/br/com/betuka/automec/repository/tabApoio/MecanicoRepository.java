package br.com.betuka.automec.repository.tabApoio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabApoio.MecanicoEntity;

public interface MecanicoRepository extends JpaRepository<MecanicoEntity, Integer> {

	@Query("select m from MecanicoEntity m where m.nomMecanico like concat('%', :nomMecanico , '%')")
	List<MecanicoEntity> pesquisarNome(@Param("nomMecanico") String nomMecanico);
	
}
