package br.com.betuka.automec.repository.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.cadastro.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

	@Query("select c from ClienteEntity c where c.nomCliente like concat('%', :nomCliente, '%')")
	List<ClienteEntity> buscarNome(@Param("nomCliente") String nomCliente);
	
	@Query("select c from ClienteEntity c where c.nroCpf = :nroCpf")
	Optional<ClienteEntity> pesquisarCpf(@Param("nroCpf") String nroCpf);
	
}
