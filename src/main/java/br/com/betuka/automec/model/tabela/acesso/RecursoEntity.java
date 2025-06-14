package br.com.betuka.automec.model.tabela.acesso;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.dto.tabela.acesso.RecursoDTO;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recurso")
public class RecursoEntity implements Serializable {

	private static final long serialVersionUID = 5496804401246987229L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_recurso")
	private int codRecurso;

	@Column(name = "des_recurso", nullable = false, length = 100, unique = true)
	private String desRecurso;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1, unique = false)
	private SituacaoEnum situacao = SituacaoEnum.A;

	public RecursoEntity() {
		super();
	}

	public RecursoEntity(int codRecurso, String desRecurso, SituacaoEnum situacao) {
		super();
		this.codRecurso = codRecurso;
		this.desRecurso = desRecurso;
		this.situacao = situacao;
	}
	
	public RecursoEntity(RecursoDTO recursoDTO) { 
		super();
	    BeanUtils.copyProperties(recursoDTO, this); 
	}
	
	public int getCodRecurso() {
		return codRecurso;
	}
	
	public void setCodRecurso(int codRecurso) {
		this.codRecurso = codRecurso;
	}

	public String getDesRecurso() {
		return desRecurso;
	}

	public void setDesRecurso(String desRecurso) {
		this.desRecurso = desRecurso;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codRecurso, desRecurso, situacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoEntity other = (RecursoEntity) obj;
		return Objects.equals(codRecurso, other.codRecurso) && 
			   Objects.equals(desRecurso, other.desRecurso)	&& 
			   Objects.equals(situacao, other.situacao);
	}

	@Override
	public String toString() {
		return "RecursoEntity [ codRecurso=" + codRecurso + 
				", descricao=" + desRecurso + 
				", situacao=" + situacao + "]";
	}
	
	public boolean estaAtivo() {
		return this.situacao.equals(SituacaoEnum.A);
	}
}
