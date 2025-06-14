package br.com.betuka.automec.model.cadastro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.cadastro.VeiculoDTO;
import br.com.betuka.automec.model.tabela.apoio.ModeloEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculo")
public class VeiculoEntity implements Serializable {

	private static final long serialVersionUID = 2736018109516882240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_veiculo")
	private int codVeiculo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_modelo", nullable = false, unique = false)
	private ModeloEntity modelo;
	
	@Column(name = "des_cor", nullable = true, unique = false, length = 25)
	private String desCor;
	
	@Column(name = "nro_placa", nullable = false, unique = true, length = 10)
	private String nroPlaca;
	
	@Column(name = "ano_veiculo", nullable = false, unique = false, length = 10)
	private String anoVeiculo;
	
	@Column(name = "des_motor", nullable = true, unique = false, length = 25)
	private String desMotor;
	
	@Column(name = "qtd_km_veiculo", nullable = false, unique = false, length = 10)
	private int qtdKmVeiculo;
	
	@Column(name = "qtd_km_dia", nullable = false, unique = false, length = 10)
	private int qtdKmDia;
	
	@Column(name = "dta_cadastro", nullable = false, unique = false)
	private Date dtaCadastro;
	
	public VeiculoEntity() {
		super();
	}
	
	public VeiculoEntity(VeiculoDTO veiculoDTO) {
		super();
		BeanUtils.copyProperties(veiculoDTO, this);
		this.modelo = new ModeloEntity(veiculoDTO.getModelo());
	}

	public VeiculoEntity(
			int codVeiculo, 
			ModeloEntity modelo, 
			String desCor, 
			String nroPlaca, 
			String anoVeiculo,
			String desMotor, 
			int qtdKmVeiculo, 
			int qtdKmDia,
			Date dtaCadastro) {
		super();
		this.codVeiculo = codVeiculo;
		this.modelo = modelo;
		this.desCor = desCor;
		this.nroPlaca = nroPlaca;
		this.anoVeiculo = anoVeiculo;
		this.desMotor = desMotor;
		this.qtdKmVeiculo = qtdKmVeiculo;
		this.qtdKmDia = qtdKmDia;
		this.dtaCadastro = dtaCadastro;
	}

	public int getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(int codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public ModeloEntity getModelo() {
		return modelo;
	}

	public void setModelo(ModeloEntity modelo) {
		this.modelo = modelo;
	}

	public String getDesCor() {
		return desCor;
	}

	public void setDesCor(String desCor) {
		this.desCor = desCor;
	}

	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}

	public String getAnoVeiculo() {
		return anoVeiculo;
	}

	public void setAnoVeiculo(String anoVeiculo) {
		this.anoVeiculo = anoVeiculo;
	}

	public String getDesMotor() {
		return desMotor;
	}

	public void setDesMotor(String desMotor) {
		this.desMotor = desMotor;
	}

	public int getQtdKmVeiculo() {
		return qtdKmVeiculo;
	}

	public void setQtdKmVeiculo(int qtdKmVeiculo) {
		this.qtdKmVeiculo = qtdKmVeiculo;
	}

	public int getQtdKmDia() {
		return qtdKmDia;
	}

	public void setQtdKmDia(int qtdKmDia) {
		this.qtdKmDia = qtdKmDia;
	}

	public Date getDtaCadastro() {
		return dtaCadastro;
	}

	public void setDtaCadastro(Date dtaCadastro) {
		this.dtaCadastro = dtaCadastro;
	}
	
	public String getDtaCadastroBR() {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		return DateFor.format(this.dtaCadastro);		
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				anoVeiculo, 
				codVeiculo, 
				desCor, 
				desMotor, 
				dtaCadastro, 
				modelo, 
				nroPlaca, 
				qtdKmDia,
				qtdKmVeiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VeiculoEntity other = (VeiculoEntity) obj;
		return Objects.equals(anoVeiculo, other.anoVeiculo) 
				&& codVeiculo == other.codVeiculo
				&& Objects.equals(desCor, other.desCor) 
				&& Objects.equals(desMotor, other.desMotor)
				&& Objects.equals(dtaCadastro, other.dtaCadastro) 
				&& Objects.equals(modelo, other.modelo)
				&& Objects.equals(nroPlaca, other.nroPlaca) 
				&& qtdKmDia == other.qtdKmDia
				&& qtdKmVeiculo == other.qtdKmVeiculo;
	}

	@Override
	public String toString() {
		return "VeiculoEntity [codVeiculo=" + codVeiculo + 
				", modelo=" + modelo.toString() + 
				", desCor=" + desCor + 
				", nroPlaca=" + nroPlaca + 
				", anoVeiculo=" + anoVeiculo + 
				", desMotor=" + desMotor + 
				", qtdKmVeiculo=" + qtdKmVeiculo + 
				", qtdKmDia=" + qtdKmDia + 
				", dtaCadastro=" + dtaCadastro + "]";
	}
}
