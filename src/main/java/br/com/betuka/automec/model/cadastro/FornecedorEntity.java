package br.com.betuka.automec.model.cadastro;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.cadastro.FornecedorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class FornecedorEntity implements Serializable {

	private static final long serialVersionUID = 1242288905292321508L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_fornecedor")
	private int codFornecedor;
	
	@Column(name = "des_fornecedor", nullable = false, length = 100, unique = true)
	private String desFornecedor;
	
	@Column(name = "nro_celular", length = 15, nullable = true, unique = false)
	private String nroCelular;
	
	@Column(name = "nro_telefone", length = 15, nullable = true, unique = false)
	private String nroTelefone;
	
	public FornecedorEntity() {
		super();
	}

	public FornecedorEntity(int codFornecedor, String desFornecedor) {
		super();
		this.codFornecedor = codFornecedor;
		this.desFornecedor = desFornecedor;
	}
	
	public FornecedorEntity(FornecedorDTO fornecedorDTO) {
		super();
		BeanUtils.copyProperties(fornecedorDTO, this);
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

	@Override
	public int hashCode() {
		return Objects.hash(codFornecedor, desFornecedor, nroCelular, nroTelefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FornecedorEntity other = (FornecedorEntity) obj;
		return codFornecedor == other.codFornecedor 
				&& Objects.equals(desFornecedor, other.desFornecedor)
				&& Objects.equals(nroCelular, other.nroCelular) 
				&& Objects.equals(nroTelefone, other.nroTelefone);
	}

	@Override
	public String toString() {
		return "FornecedorEntity [codFornecedor=" + codFornecedor + 
				", desFornecedor=" + desFornecedor + 
				", nroCelular="	+ nroCelular + 
				", nroTelefone=" + nroTelefone + "]";
	}
}
