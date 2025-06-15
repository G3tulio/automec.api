package br.com.betuka.automec.repository.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.cadastro.FornecedorEntity;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Integer> {

	@Query("select f from FornecedorEntity f where f.desFornecedor = :desFornecedor")
	Optional<FornecedorEntity> pesquisarDescricao(@Param("desFornecedor") String desFornecedor);

	@Query("select f from FornecedorEntity f where f.desFornecedor like concat('%', :desFornecedor, '%')")
	List<FornecedorEntity> buscarDescricao(@Param("desFornecedor") String desFornecedor);
		
}
