package br.com.betuka.automec.service.tabApoio;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.TabApoio.GrupoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabApoio.GrupoEntity;
import br.com.betuka.automec.repository.tabApoio.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<GrupoDTO> listar() {
		return GrupoDTO.toList(this.grupoRepository.findAll());
	}
	
	private void validarEntrada(GrupoDTO grupoDTO) throws ValidationException, Exception {
		if (grupoDTO.getCodGrupo() != 0) {
			this.pesquisarCodigo(grupoDTO.getCodGrupo());
		}
		
		if (grupoDTO.getDesGrupo().isBlank()) {
			throw new ValidationException(Constants.GRUPO_DESCRICAO_OBRIGATORIA);
		}
	}
	
	public void gravar(GrupoDTO grupoDTO) throws ValidationException, Exception {
		this.validarEntrada(grupoDTO);
		this.grupoRepository.save(new GrupoEntity(grupoDTO));
	}
	
	public void deletar(int codGrupo) throws ValidationException, Exception {
		this.pesquisarCodigo(codGrupo);
		
		try {
			this.grupoRepository.deleteById(codGrupo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public GrupoDTO pesquisarCodigo(int codGrupo) throws ValidationException, Exception {
		try {
			return new GrupoDTO(this.grupoRepository.findById(codGrupo).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.GRUPO_INEXISTENTE);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<GrupoDTO> pesquisarDescricao(String desGrupo) throws Exception {
		try {
			return GrupoDTO.toList(this.grupoRepository.pesquisarDescricao(desGrupo));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
