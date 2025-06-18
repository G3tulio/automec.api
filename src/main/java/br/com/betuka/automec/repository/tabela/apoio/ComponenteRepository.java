package br.com.betuka.automec.repository.tabela.apoio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.dto.tabela.apoio.VWComponentesSistemaDTO;
import br.com.betuka.automec.model.tabela.apoio.ComponenteEntity;

public interface ComponenteRepository extends JpaRepository<ComponenteEntity, Integer> {

	@Query("select m from ComponenteEntity m where m.desComponente = :desComponente")
	Optional<ComponenteEntity> pesquisarDescricao(@Param("desComponente") String desComponente);
	
	@Query(value = "select vwcs.cod_componente, vwcs.des_componente from vw_componentes_sistena vwcs where vwcs.cod_sistema = :cod_sistema", nativeQuery = true)
	Optional<List<VWComponentesSistemaDTO>> buscarComponentesPorSistema( @Param("cod_sistema") int cod_sistema );	
	
//	@Query("select (count(v) > 0) as existe from VeiculoEntity v where v.componente.codComponente = :codComponente")
//	boolean existeVeiculoComponente(@Param("codComponente") int codComponente);	
}
