package br.com.betuka.automec.dto.cadastro;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.ComponenteDTO;
import br.com.betuka.automec.model.cadastro.ProdutoEntity;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ProdutoDTO {
	
	private int codProduto;
	
	private String nomProduto;
	
	private String desTecnica;
	
	private String undMedida; // "UN", "CX", "LT"
	
	private String indRevisao; // "S", "N"
	
	private int vdaUtil;
	
	private int qtdMinima;
	
	private BigDecimal pctLucro;
	
	private FabricanteDTO fabricanteDTO;
	
	private ComponenteDTO componenteDTO;
	
	private int qtdAtual;
	
	private BigDecimal vlrCusto;
	
	private BigDecimal vlrVenda;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum indSituacao = SituacaoEnum.A;
	
	public ProdutoDTO() {
		super();
	}
	
	public ProdutoDTO( int codProduto, 
			           String nomProduto, 
			           String desTecnica, 
			           String undMedida, 
			           String indRevisao,
			           int vdaUtil, 
			           int qtdMinima, 
			           BigDecimal pctLucro, 
			           FabricanteDTO fabricanteDTO, 
			           ComponenteDTO componenteDTO,
			           int qtdAtual, 
			           BigDecimal vlrCusto, 
			           BigDecimal vlrVenda, 
			           SituacaoEnum indSituacao ) {
		super();
		this.codProduto = codProduto;
		this.nomProduto = nomProduto;
		this.desTecnica = desTecnica;
		this.undMedida = undMedida;
		this.indRevisao = indRevisao;
		this.vdaUtil = vdaUtil;
		this.qtdMinima = qtdMinima;
		this.pctLucro = pctLucro;
		this.fabricanteDTO = fabricanteDTO;
		this.componenteDTO = componenteDTO;
		this.qtdAtual = qtdAtual;
		this.vlrCusto = vlrCusto;
		this.vlrVenda = vlrVenda;
		this.indSituacao = indSituacao;
	}
	
	public ProdutoDTO(ProdutoEntity produtoEntity) {
		super();
		BeanUtils.copyProperties(produtoEntity, this);
		this.fabricanteDTO = new FabricanteDTO(produtoEntity.getFabricanteEntity());
		this.componenteDTO = new ComponenteDTO(produtoEntity.getComponenteEntity());
	}
	
	public static List<ProdutoDTO> toList(List<ProdutoEntity> lista) {
		return lista.stream().map(ProdutoDTO::new).toList();
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomProduto() {
		return nomProduto;
	}

	public void setNomProduto(String nomProduto) {
		this.nomProduto = nomProduto;
	}

	public String getDesTecnica() {
		return desTecnica;
	}

	public void setDesTecnica(String desTecnica) {
		this.desTecnica = desTecnica;
	}

	public String getUndMedida() {
		return undMedida;
	}

	public void setUndMedida(String undMedida) {
		this.undMedida = undMedida;
	}

	public String getIndRevisao() {
		return indRevisao;
	}

	public void setIndRevisao(String indRevisao) {
		this.indRevisao = indRevisao;
	}

	public int getVdaUtil() {
		return vdaUtil;
	}

	public void setVdaUtil(int vdaUtil) {
		this.vdaUtil = vdaUtil;
	}

	public int getQtdMinima() {
		return qtdMinima;
	}

	public void setQtdMinima(int qtdMinima) {
		this.qtdMinima = qtdMinima;
	}

	public BigDecimal getPctLucro() {
		return pctLucro;
	}

	public void setPctLucro(BigDecimal pctLucro) {
		this.pctLucro = pctLucro;
	}

	public FabricanteDTO getFabricanteDTO() {
		return fabricanteDTO;
	}

	public void setFabricanteDTO(FabricanteDTO fabricanteDTO) {
		this.fabricanteDTO = fabricanteDTO;
	}

	public ComponenteDTO getComponenteDTO() {
		return componenteDTO;
	}

	public void setComponenteDTO(ComponenteDTO componenteDTO) {
		this.componenteDTO = componenteDTO;
	}

	public int getQtdAtual() {
		return qtdAtual;
	}

	public void setQtdAtual(int qtdAtual) {
		this.qtdAtual = qtdAtual;
	}

	public BigDecimal getVlrCusto() {
		return vlrCusto;
	}

	public void setVlrCusto(BigDecimal vlrCusto) {
		this.vlrCusto = vlrCusto;
	}

	public BigDecimal getVlrVenda() {
		return vlrVenda;
	}

	public void setVlrVenda(BigDecimal vlrVenda) {
		this.vlrVenda = vlrVenda;
	}

	public SituacaoEnum getIndSituacao() {
		return indSituacao;
	}

	public void setIndSituacao(SituacaoEnum indSituacao) {
		this.indSituacao = indSituacao;
	}
	
	public boolean estaAtivo() {
		return this.indSituacao.equals(SituacaoEnum.A);
	}
}
