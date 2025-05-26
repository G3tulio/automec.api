package br.com.betuka.automec.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.ClienteEntity;

public class ClienteDTO {

	private int codCliente;
	private String nomCliente;
	private String nroCpf;
	private String nroCelular;
	private String nroTelefone;
	private String email;
	private String obs;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(
			int codCliente, 
			String nomCliente, 
			String nroCpf, 
			String nroCelular, 
			String nroTelefone,
			String email, 
			String obs) {
		super();
		this.codCliente = codCliente;
		this.nomCliente = nomCliente;
		this.nroCpf = nroCpf;
		this.nroCelular = nroCelular;
		this.nroTelefone = nroTelefone;
		this.email = email;
		this.obs = obs;
	}
	
	public ClienteDTO(ClienteEntity clienteEntity) {
		super();
		BeanUtils.copyProperties(clienteEntity, this);
	}
	
	public static List<ClienteDTO> toList(List<ClienteEntity> lista) {
		return lista.stream().map(ClienteDTO::new).toList();
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getNroCpf() {
		return nroCpf;
	}

	public void setNroCpf(String nroCpf) {
		this.nroCpf = nroCpf;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}
