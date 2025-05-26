package br.com.betuka.automec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.VeiculoEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {

	@Query("SELECT v FROM VeiculoEntity v WHERE v.nroPlaca = :nroPlaca")
	Optional<VeiculoEntity> pesquisarNroPlaca(@Param("nroPlaca") String nroPlaca);
	
}
