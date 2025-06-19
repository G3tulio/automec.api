package br.com.betuka.automec.service.tabela.apoio;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.tabela.apoio.KitDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.tabela.apoio.KitEntity;
import br.com.betuka.automec.repository.tabela.apoio.KitRepository;

@Service
public class KitService {

	@Autowired
	private KitRepository kitRepository;
	
	public List<KitDTO> listar() {
		return KitDTO.toList(this.kitRepository.findAll());
	}
	
	public void validarEntrada(KitDTO kitDTO) throws ValidationException, Exception {
		if (kitDTO.getCodKit() != 0) {
			this.pesquisarCodigo(kitDTO.getCodKit()); // Caso não encontre já levanta uma Exception
		}
		
		if (kitDTO.getDesKit().isEmpty()) {
			throw new ValidationException(Constants.KIT_DESCRICAO_OBRIGATORIA);
		}
		
		KitDTO oKitDTO = null;
		
		try {
			oKitDTO = this.pesquisarDescricao(kitDTO.getDesKit());
		} catch (ValidationException e) {
			// Neste caso não trata a exceção
		}
		
		if (Objects.nonNull(oKitDTO)) {
			if (kitDTO.getCodKit() == 0) {
				throw new ValidationException(Constants.KIT_JA_CADASTRADO);
			} else {
				if (kitDTO.getCodKit() != oKitDTO.getCodKit()) {
					throw new ValidationException(Constants.KIT_JA_CADASTRADO);
				}
			}
		}
	}
	
	public void gravar(KitDTO kitDTO) throws ValidationException, Exception {
		this.validarEntrada(kitDTO);
		this.kitRepository.save( new KitEntity(kitDTO) );
	}
    
//    private boolean existeModeloKit(int codKit) throws ValidationException, Exception {
//    	try {
//    		return this.kitRepository.existeModeloKit(codKit);
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//    }
	
	public void deletar(int codKit) throws ValidationException, Exception {
		this.pesquisarCodigo(codKit); // Caso não encontre levanta uma ValidationException
		
//		if (this.existeModeloKit(codKit)) {
//			throw new ValidationException(Constants.KIT_UTILIZADA);
//		}
//		
		try {
			this.kitRepository.deleteById(codKit);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public KitDTO pesquisarCodigo(int codKit) throws ValidationException, Exception {
		try {
			return new KitDTO(this.kitRepository.findById(codKit).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.KIT_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public KitDTO pesquisarDescricao(String desKit) throws ValidationException, Exception {
		try {
			return new KitDTO(this.kitRepository.pesquisarDescricao(desKit).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.KIT_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<KitDTO> buscarDescricao(String desKit) throws ValidationException, Exception {
		try {
			return KitDTO.toList(this.kitRepository.buscarDescricao(desKit));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
