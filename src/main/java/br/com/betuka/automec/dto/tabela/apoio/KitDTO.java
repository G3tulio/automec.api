package br.com.betuka.automec.dto.tabela.apoio;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.betuka.automec.model.tabela.apoio.KitEntity;

public class KitDTO {
	
	private int codKit;
	private String desKit;
	
	public KitDTO() {
		super();
	}

	public KitDTO(int codKit, String desKit) {
		super();
		this.codKit = codKit;
		this.desKit = desKit;
	}
	
	public KitDTO(KitEntity kitEntity) {
		super();
		BeanUtils.copyProperties(kitEntity, this);
	}
	
	public static List<KitDTO> toList(List<KitEntity> lista) {
		return lista.stream().map(KitDTO::new).toList();
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
}
