package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.MecanicoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.MecanicoEntity;
import br.com.betuka.automec.repository.tabela.apoio.MecanicoRepository;

@Service
public class MecanicoService {

	@Autowired
	private MecanicoRepository mecanicoRepository;
	
 	public List<MecanicoDTO> listar() {
		return MecanicoDTO.toList(this.mecanicoRepository.findAll());
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
		
		MecanicoDTO oMecanicoDTO = null;
		
		try {
			oMecanicoDTO = this.pesquisarNome(mecanicoDTO.getNomMecanico());
		} catch (ValidationException e) {
			System.out.println("Não encontrou o mecanico.");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oMecanicoDTO)) {
			if (mecanicoDTO.getCodMecanico() == 0) {
				throw new ValidationException(Constants.MECANICO_JA_CADASTRADO);
			} else {
				if (mecanicoDTO.getCodMecanico() != oMecanicoDTO.getCodMecanico()) {
					throw new ValidationException(Constants.MECANICO_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(MecanicoDTO mecanicoDTO) throws ValidationException, Exception {
		this.validarEntrada(mecanicoDTO);
		this.mecanicoRepository.save( new MecanicoEntity(mecanicoDTO) );
	}
	
	public void deletar(int codMecanico) throws ValidationException, Exception {
		this.pesquisarCodigo(codMecanico); // Caso não encontre levanta ValidationException
		
		// Mais a frente vericar se o mecanico esta realcionado ao outra entidade [ FK ]
		
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
	
	public MecanicoDTO pesquisarNome(String nomMecanico) throws ValidationException, Exception {
		try {
			return new MecanicoDTO(this.mecanicoRepository.pesquisarNome(nomMecanico).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.MECANICO_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
