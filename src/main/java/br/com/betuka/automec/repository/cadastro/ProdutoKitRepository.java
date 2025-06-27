package br.com.betuka.automec.repository.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.betuka.automec.dto.cadastro.VWProdutoKitDTO;
import br.com.betuka.automec.model.cadastro.ProdutoKitEntity;

public interface ProdutoKitRepository extends JpaRepository<ProdutoKitEntity, Integer> {
	
	@Query("select pk from ProdutoKitEntity pk where pk.codKit = :codKit and pk.codProduto = :codProduto")
	Optional<ProdutoKitEntity> pesquisarItem(@Param("codKit") int codKit, @Param("codProduto") int codProduto);
	
	@Query(value = "select vpk.cod_produto, vpk.qtd_produto from automec_desenv.vw_produto_kit vpk where vpk.cod_kit = :codKit", nativeQuery = true)
	Optional<List<VWProdutoKitDTO>> listarProdutosKit( @Param("codKit") int codKit);
	
}
