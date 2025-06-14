package br.com.betuka.automec.service.tabApoio;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.TabApoio.MecanicoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabApoio.MecanicoEntity;
import br.com.betuka.automec.repository.tabApoio.MecanicoRepository;

@Service
public class MecanicoService {

	@Autowired
	private MecanicoRepository mecanicoRepository;
	
	public List<MecanicoDTO> listar() {
		List<MecanicoEntity> lista = this.mecanicoRepository.findAll();
		return lista.stream().map(MecanicoDTO::new).toList();
	}
	
	private void validarEntrada(MecanicoDTO mecanicoDTO) throws ValidationException, Exception {
		if (mecanicoDTO.getCodMecanico() != 0) {
			this.pesquisarCodigo(mecanicoDTO.getCodMecanico());
		}
		
		if (mecanicoDTO.getNomMecanico().isBlank()) {
			throw new ValidationException(Constants.MECANICO_NOME_OBRIGATORIA);
		}
		
		if (mecanicoDTO.getNroCelular().isEmpty()) {
			throw new ValidationException(Constants.MECANICO_CELULAR_OBRIGATORIA);
		}
	}
	
	public void adicionar(MecanicoDTO mecanicoDTO) throws ValidationException, Exception {
		this.validarEntrada(mecanicoDTO);
		this.mecanicoRepository.save( new MecanicoEntity(mecanicoDTO) );
	}
	
	public void atualizar(MecanicoDTO mecanicoDTO) throws ValidationException, Exception {
		this.validarEntrada(mecanicoDTO);
		this.mecanicoRepository.save( new MecanicoEntity(mecanicoDTO) );
	}
	
	public void deletar(int codMecanico) throws ValidationException, Exception {
		this.pesquisarCodigo(codMecanico); // Caso não encontre levanta uma ValidationException
		
		try {
			this.mecanicoRepository.deleteById(codMecanico);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public MecanicoDTO pesquisarCodigo(int codMecanico) throws ValidationException, Exception {
		try {
			return new MecanicoDTO(this.mecanicoRepository.findById(codMecanico).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MECANICO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<MecanicoDTO> pesquisarNome(String nomMecanico) throws ValidationException, Exception {
		try {
			List<MecanicoEntity> lista = this.mecanicoRepository.pesquisarNome(nomMecanico);
			return lista.stream().map(MecanicoDTO::new).toList();
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MECANICO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
