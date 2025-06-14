package br.com.betuka.automec.repository.cadastro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.cadastro.VeiculoEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {

	@Query("select v from VeiculoEntity v where v.nroPlaca = :nroPlaca")
	Optional<VeiculoEntity> pesquisarNroPlaca(@Param("nroPlaca") String nroPlaca);
	
}
