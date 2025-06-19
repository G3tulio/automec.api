package br.com.betuka.automec.model.tabela.apoio;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.apoio.KitDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kit")
public class KitEntity implements Serializable {
	
	private static final long serialVersionUID = 9173357058290012906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_kit")
	private int codKit;
	
	@Column(name = "des_kit", nullable = false, length = 100, unique = true)
	private String desKit;
	
	public KitEntity() {
		super();
	}

	public KitEntity(int codKit, String desKit) {
		super();
		this.codKit = codKit;
		this.desKit = desKit;
	}
	
	public KitEntity(KitDTO kitDTO) {
		super();
		BeanUtils.copyProperties(kitDTO, this);
	}
	
	public int getCodKit() {
		return codKit;
	}

	public void setCodKit(int codKit) {
		this.codKit = codKit;
	}

	public String getDesKit() {
		return desKit;
	}

	public void setDesKit(String desKit) {
		this.desKit = desKit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codKit, desKit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KitEntity other = (KitEntity) obj;
		return Objects.equals(codKit, other.codKit) && 
			   Objects.equals(desKit, other.desKit);
	}

	@Override
	public String toString() {
		return "KitEntity [codKit=" + codKit + ", desKit=" + desKit + "]";
	}
}
