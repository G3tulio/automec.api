package br.com.betuka.automec.model.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.cadastro.ProdutoDTO;
import br.com.betuka.automec.model.tabela.apoio.ComponenteEntity;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class ProdutoEntity implements Serializable {
	
	private static final long serialVersionUID = 6572849082714691092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_produto")
	private int codProduto;
	
	@Column(name = "nom_produto", nullable = false, unique = true, length = 100)
	private String nomProduto;
	
	@Column(name = "des_tecnica", nullable = true, unique = false, length = 500)
	private String desTecnica;	
	
	@Column(name = "und_medida", nullable = false, unique = false, length = 5)
	private String undMedida; // "UN", "CX", "LT"
	
	@Column(name = "ind_revisao", nullable = false, unique = false, length = 1, columnDefinition = "N")
	private String indRevisao; // "S", "N"
	
	@Column(name = "vda_util", nullable = false, unique = false, columnDefinition = "0")
	private int vdaUtil;
	
	@Column(name = "qtd_minima", nullable = false, unique = false, columnDefinition = "0")
	private int qtdMinima;
	
	@Column(name = "pct_lucro", nullable = false, unique = false, columnDefinition = "0")
	private BigDecimal pctLucro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_fabricante", nullable = false, unique = false)
	private FabricanteEntity fabricanteEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_componente", nullable = false, unique = false)
	private ComponenteEntity componenteEntity;
	
	@Column(name = "qtd_atual", nullable = false, unique = false, columnDefinition = "0")
	private int qtdAtual;
	
	@Column(name = "vlr_custo", nullable = false, unique = false, columnDefinition = "0")
	private BigDecimal vlrCusto;
	
	@Column(name = "vlr_venda", nullable = false, unique = false, columnDefinition = "0")
	private BigDecimal vlrVenda;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ind_situacao", nullable = false, unique = false, length = 1)
	private SituacaoEnum indSituacao = SituacaoEnum.A;
	
	public ProdutoEntity() {
		super();
	}
	
	public ProdutoEntity(ProdutoDTO produtoDTO) {
		super();
		BeanUtils.copyProperties(produtoDTO, this);
		this.fabricanteEntity = new FabricanteEntity(produtoDTO.getFabricanteDTO());
		this.componenteEntity = new ComponenteEntity(produtoDTO.getComponenteDTO());
	}
	
	public ProdutoEntity( 
			int codProduto, 
			String nomProduto, 
			String desTecnica, 
			String undMedida, 
			String indRevisao,
			int vdaUtil, 
			int qtdMinima, 
			BigDecimal pctLucro, 
			FabricanteEntity fabricanteEntity, 
			ComponenteEntity componenteEntity,
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
		this.fabricanteEntity = fabricanteEntity;
		this.componenteEntity = componenteEntity;
		this.qtdAtual = qtdAtual;
		this.vlrCusto = vlrCusto;
		this.vlrVenda = vlrVenda;
		this.indSituacao = indSituacao;
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

	public FabricanteEntity getFabricanteEntity() {
		return fabricanteEntity;
	}

	public void setFabricanteEntity(FabricanteEntity fabricanteEntity) {
		this.fabricanteEntity = fabricanteEntity;
	}

	public ComponenteEntity getComponenteEntity() {
		return componenteEntity;
	}

	public void setComponenteEntity(ComponenteEntity componenteEntity) {
		this.componenteEntity = componenteEntity;
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
	
	@Override
	public int hashCode() {
		return Objects.hash( codProduto, 
							 componenteEntity, 
							 desTecnica, 
							 fabricanteEntity, 
							 indRevisao, 
							 indSituacao,
							 nomProduto, 
							 pctLucro, 
							 qtdAtual, 
							 qtdMinima, 
							 undMedida, 
							 vdaUtil, 
							 vlrCusto, 
							 vlrVenda );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoEntity other = (ProdutoEntity) obj;
		return codProduto == other.codProduto 
				&& Objects.equals(componenteEntity, other.componenteEntity)
				&& Objects.equals(desTecnica, other.desTecnica)
				&& Objects.equals(fabricanteEntity, other.fabricanteEntity)
				&& Objects.equals(indRevisao, other.indRevisao) 
				&& indSituacao == other.indSituacao
				&& Objects.equals(nomProduto, other.nomProduto) 
				&& Objects.equals(pctLucro, other.pctLucro)
				&& qtdAtual == other.qtdAtual 
				&& qtdMinima == other.qtdMinima
				&& Objects.equals(undMedida, other.undMedida) 
				&& vdaUtil == other.vdaUtil
				&& Objects.equals(vlrCusto, other.vlrCusto) 
				&& Objects.equals(vlrVenda, other.vlrVenda);
	}

	@Override
	public String toString() {
		return "ProdutoEntity [codProduto=" + codProduto + 
				", nomProduto=" + nomProduto + 
				", desTecnica=" + desTecnica +
				", undMedida=" + undMedida + 
				", indRevisao=" + indRevisao + 
				", vdaUtil=" + vdaUtil + 
				", qtdMinima=" + qtdMinima + 
				", pctLucro=" + pctLucro + 
				", fabricanteEntity=" + fabricanteEntity.toString() + 
				", componenteEntity=" + componenteEntity.toString() + 
				", qtdAtual=" + qtdAtual + 
				", vlrCusto=" + vlrCusto + 
				", vlrVenda=" + vlrVenda + 
				", indSituacao=" + indSituacao + "]";
	}

}
