package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.MecanicoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mecanico")
public class MecanicoEntity implements Serializable {

	private static final long serialVersionUID = 1397689438315957753L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_mecanico")
	private int codMecanico;
	
	@Column(name = "nom_mecanico", length = 100, unique = false, nullable = true)
	private String nomMecanico;
	
	@Column(name = "nro_celular", length = 15, unique = false, nullable = true)
	private String nroCelular;
	
	public MecanicoEntity() {
		super();
	}

	public MecanicoEntity(int codMecanico, String nomMecanico, String nroCelular) {
		super();
		this.codMecanico = codMecanico;
		this.nomMecanico = nomMecanico;
		this.nroCelular = nroCelular;
	}
	
	public MecanicoEntity(MecanicoDTO mecanicoDTO) {
		super();
		BeanUtils.copyProperties(mecanicoDTO, this);
	}

	public int getCodMecanico() {
		return codMecanico;
	}

	public void setCodMecanico(int codMecanico) {
		this.codMecanico = codMecanico;
	}

	public String getNomMecanico() {
		return nomMecanico;
	}

	public void setNomMecanico(String nomMecanico) {
		this.nomMecanico = nomMecanico;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codMecanico, nomMecanico, nroCelular);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MecanicoEntity other = (MecanicoEntity) obj;
		return codMecanico == other.codMecanico 
				&& Objects.equals(nomMecanico, other.nomMecanico)
				&& Objects.equals(nroCelular, other.nroCelular);
	}

	@Override
	public String toString() {
		return "MecanicoEntity [codMecanico=" + codMecanico + ", nomMecanico=" + nomMecanico + ", nroCelular="	+ nroCelular + "]";
	}
}
