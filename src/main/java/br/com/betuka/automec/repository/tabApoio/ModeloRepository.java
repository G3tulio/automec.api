package br.com.betuka.automec.repository.tabApoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.dto.TabApoio.VWModelosMarcaDTO;
import br.com.betuka.automec.model.tabApoio.ModeloEntity;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {

	@Query("select m from ModeloEntity m where m.desModelo = :desModelo")
	Optional<ModeloEntity> pesquisarDescricao(@Param("desModelo") String desModelo);
	
	@Query(value = "select cod_modelo, des_modelo from vw_modelos_marca where cod_marca = :cod_marca", nativeQuery = true)
	Optional<List<VWModelosMarcaDTO>> buscarModelosPorMarca( @Param("cod_marca") int cod_marca );	
//	
//	@Query("select v from VeiculoEntity v where v.modelo.codModelo = :codModelo")
//	Optional<ModeloEntity> pesquisarModeloVeiculo(@Param("codModelo") int codModelo);	
}
