package br.com.betuka.automec.repository.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.cadastro.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

	@Query("select p from ProdutoEntity p where p.nomProduto = :nomProduto")
	Optional<ProdutoEntity> pesquisarNome(@Param("nomProduto") String nomProduto);

	@Query("select p from ProdutoEntity p where p.nomProduto like concat('%', :nomProduto, '%')")
	List<ProdutoEntity> buscarNome(@Param("nomProduto") String nomProduto);

	@Query("select (count(p) > 0) as existe from ProdutoEntity p where p.fabricanteEntity.codFabricante = :codFabricante")
	boolean existeProdutosFabricante(@Param("codFabricante") int codFabricante);	
	
}
