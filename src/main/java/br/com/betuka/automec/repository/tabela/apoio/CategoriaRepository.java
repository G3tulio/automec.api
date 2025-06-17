package br.com.betuka.automec.repository.tabela.apoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.apoio.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
	
	@Query("select c from CategoriaEntity c where c.desCategoria = :desCategoria")
	Optional<CategoriaEntity> pesquisarDescricao(@Param("desCategoria") String desCategoria);
	
	@Query("select c from CategoriaEntity c where c.desCategoria like concat('%', :desCategoria, '%')")
	List<CategoriaEntity> buscarDescricao(@Param("desCategoria") String desCategoria);
	
//	@Query("select (count(*) > 0) as existe from SubCategoriaEntity sc where sc.categoria.codCategoria = :codCategoria")
//	boolean existeSubCategoriaCategoria(@Param("codCategoria") int codCategoria);
	
}
