package br.com.betuka.automec.dto.cadastro;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.cadastro.FornecedorEntity;

public class FornecedorDTO {
	
	private int codFornecedor;
	
	private String desFornecedor;
	
	private String nroCelular;
	
	private String nroTelefone;
	
	public FornecedorDTO() {
		super();
	}

	public FornecedorDTO(int codFornecedor, String desFornecedor) {
		super();
		this.codFornecedor = codFornecedor;
		this.desFornecedor = desFornecedor;
	}
	
	public FornecedorDTO(FornecedorEntity fornecedorEntity) {
		super();
		BeanUtils.copyProperties(fornecedorEntity, this);
	}
	
	public static List<FornecedorDTO> toList(List<FornecedorEntity> lista) {
		return lista.stream().map(FornecedorDTO::new).toList();
	}

	public int getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(int codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public String getDesFornecedor() {
		return desFornecedor;
	}

	public void setDesFornecedor(String desFornecedor) {
		this.desFornecedor = desFornecedor;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getNroTelefone() {
		return nroTelefone;
	}

	public void setNroTelefone(String nroTelefone) {
		this.nroTelefone = nroTelefone;
	}
}
