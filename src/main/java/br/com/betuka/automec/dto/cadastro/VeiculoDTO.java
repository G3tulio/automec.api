package br.com.betuka.automec.dto.cadastro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.ModeloDTO;
import br.com.betuka.automec.model.cadastro.VeiculoEntity;

public class VeiculoDTO {
	
	private int codVeiculo;
	
	private ModeloDTO modeloDTO;
	
	private String desCor;
	
	private String nroPlaca;
	
	private String anoVeiculo;
	
	private String desMotor;
	
	private int qtdKmVeiculo;
	
	private int qtdKmDia;
	
	private Date dtaCadastro;
	
	public VeiculoDTO() {
		super();
	}

	public VeiculoDTO(VeiculoEntity veiculoEntity) {
		super();
		BeanUtils.copyProperties(veiculoEntity, this);
		this.modeloDTO = new ModeloDTO(veiculoEntity.getModelo());
	}

	public VeiculoDTO(
				int codVeiculo, 
				ModeloDTO modeloDTO, 
				String desCor, 
				String nroPlaca, 
				String anoVeiculo,
				String desMotor, 
				int qtdKmVeiculo, 
				int qtdKmDia, 
				Date dtaCadastro) {
		super();
		this.codVeiculo = codVeiculo;
		this.modeloDTO = modeloDTO;
		this.desCor = desCor;
		this.nroPlaca = nroPlaca;
		this.anoVeiculo = anoVeiculo;
		this.desMotor = desMotor;
		this.qtdKmVeiculo = qtdKmVeiculo;
		this.qtdKmDia = qtdKmDia;
		this.dtaCadastro = dtaCadastro;
	}
	
	public static List<VeiculoDTO> toList(List<VeiculoEntity> lista) {
		return lista.stream().map(VeiculoDTO::new).toList();
	}

	public int getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(int codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public ModeloDTO getModelo() {
		return modeloDTO;
	}

	public void setModelo(ModeloDTO modeloDTO) {
		this.modeloDTO = modeloDTO;
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
}
