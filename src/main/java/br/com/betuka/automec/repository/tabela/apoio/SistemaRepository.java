package br.com.betuka.automec.repository.tabela.apoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.model.tabela.apoio.SistemaEntity;

public interface SistemaRepository extends JpaRepository<SistemaEntity, Integer> {
	
	@Query("select s from SistemaEntity s where s.desSistema= :desSistema")
	Optional<SistemaEntity> pesquisarDescricao(@Param("desSistema") String desSistema);
	
	@Query("select s from SistemaEntity s where s.desSistema like concat('%', :desSistema, '%')")
	List<SistemaEntity> buscarDescricao(@Param("desSistema") String desSistema);
	
//	@Query("select (count(*) > 0) as existe from SubCategoriaEntity sc where sc.categoria.codCategoria = :codCategoria")
//	boolean existeSubCategoriaCategoria(@Param("codCategoria") int codCategoria);
	
}
