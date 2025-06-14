package br.com.betuka.automec.repository.tabela.apoio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.apoio.MecanicoEntity;

public interface MecanicoRepository extends JpaRepository<MecanicoEntity, Integer> {

	@Query("select m from MecanicoEntity m where m.nomMecanico = :nomMecanico")
	Optional<MecanicoEntity> pesquisarNome(@Param("nomMecanico") String nomMecanico);
	
}
