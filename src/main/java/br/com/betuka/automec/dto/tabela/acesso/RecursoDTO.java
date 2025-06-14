package br.com.betuka.automec.dto.tabAcesso;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabAcesso.RecursoEntity;
import br.com.betuka.automec.util.enums.SituacaoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RecursoDTO {

	private int codRecurso;
	
	private String desRecurso;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao = SituacaoEnum.A;
	
	public RecursoDTO() {
		super();
	}
	
	public RecursoDTO(int codRecurso, String desRecurso, SituacaoEnum situacao) {
		super();
		this.codRecurso = codRecurso;
		this.desRecurso = desRecurso;
		this.situacao = situacao;
	}
	
	public RecursoDTO(RecursoEntity recursoEntity) {
		super();
		BeanUtils.copyProperties(recursoEntity, this);
	}
	
	public static List<RecursoDTO> toList(List<RecursoEntity> lista) {
		return lista.stream().map(RecursoDTO::new).toList();
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
}
