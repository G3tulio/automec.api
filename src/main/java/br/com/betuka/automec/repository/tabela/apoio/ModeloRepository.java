package br.com.betuka.automec.repository.tabela.apoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.dto.tabela.apoio.VWModelosMarcaDTO;
import br.com.betuka.automec.model.tabela.apoio.ModeloEntity;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {

	@Query("select m from ModeloEntity m where m.desModelo = :desModelo")
	Optional<ModeloEntity> pesquisarDescricao(@Param("desModelo") String desModelo);
	
	@Query(value = "select cod_modelo, des_modelo from vw_modelos_marca where cod_marca = :cod_marca", nativeQuery = true)
	Optional<List<VWModelosMarcaDTO>> buscarModelosPorMarca( @Param("cod_marca") int cod_marca );	
	
	@Query("select (count(v) > 0) as existe from VeiculoEntity v where v.modelo.codModelo = :codModelo")
	boolean existeVeiculoModelo(@Param("codModelo") int codModelo);	
}
