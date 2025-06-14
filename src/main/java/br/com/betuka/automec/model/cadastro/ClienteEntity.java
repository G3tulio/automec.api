package br.com.betuka.automec.model.cadastro;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.cadastro.ClienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteEntity implements Serializable {
	
	private static final long serialVersionUID = 6510050381946931316L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cliente")
	private int codCliente;
	
	@Column(name = "nom_cliente", length = 100, nullable = false, unique = false)
	private String nomCliente;
	
	@Column(name = "nro_cpf", length = 11, nullable = false, unique = true)
	private String nroCpf;
	
	@Column(name = "nro_celular", length = 15, nullable = false, unique = false)
	private String nroCelular;
	
	@Column(name = "nro_telefone", length = 15, nullable = true, unique = false)
	private String nroTelefone;
	
	@Column(name = "email", length = 100, nullable = true, unique = false)
	private String email;
	
	@Column(name = "obs", length = 1000, nullable = true, unique = false)
	private String obs;	

	public ClienteEntity() {
		super();
	}

	public ClienteEntity(
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
	
	public ClienteEntity(ClienteDTO clienteDTO) {
		super();
		BeanUtils.copyProperties(clienteDTO, this);
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

	@Override
	public int hashCode() {
		return Objects.hash(codCliente, email, nomCliente, nroCelular, nroCpf, nroTelefone, obs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteEntity other = (ClienteEntity) obj;
		return codCliente == other.codCliente 
				&& Objects.equals(email, other.email)
				&& Objects.equals(nomCliente, other.nomCliente) 
				&& Objects.equals(nroCelular, other.nroCelular)
				&& Objects.equals(nroCpf, other.nroCpf) 
				&& Objects.equals(nroTelefone, other.nroTelefone)
				&& Objects.equals(obs, other.obs);
	}

	@Override
	public String toString() {
		return "ClienteEntity [codCliente=" + codCliente + 
				", nomCliente=" + nomCliente + 
				", nroCpf=" + nroCpf + 
				", nroCelular=" + nroCelular + 
				", nroTelefone=" + nroTelefone + 
				", email=" + email + 
				", obs=" + obs + "]";
	}
}