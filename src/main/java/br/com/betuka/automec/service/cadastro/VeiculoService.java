package br.com.betuka.automec.service.cadastro;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.VeiculoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.VeiculoEntity;
import br.com.betuka.automec.repository.cadastro.VeiculoRepository;
import br.com.betuka.automec.service.tabela.apoio.ModeloService;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ModeloService modeloService;
	
	public List<VeiculoDTO> listar() {
		List<VeiculoEntity> lista = this.veiculoRepository.findAll();
		return lista.stream().map(VeiculoDTO::new).toList();
	}
	
	private void validarEntrada(VeiculoDTO veiculoDTO) throws ValidationException, Exception {
		if (veiculoDTO.getCodVeiculo() != 0) {
			this.pesquisarCodigo(veiculoDTO.getCodVeiculo()); // Caso não encontre levanta ValidationException
		}
		
		this.modeloService.pesquisarCodigo(veiculoDTO.getModelo().getCodModelo()); // Caso não encontre levanta ValidationException
		
		if (veiculoDTO.getNroPlaca().isBlank()) {
			throw new ValidationException(Constants.VEICULO_PLACA_OBRIGATORIA);
		}
		
		VeiculoDTO oVeiculoDTO = null;
		
		try {
			oVeiculoDTO = this.pesuisarNroPlaca(veiculoDTO.getNroPlaca());
		} catch (ValidationException e) {
			// Não encontrou o número da placa, não levanta excessão
			// System.out.println("Não encontrou o número da placa");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		// Encontrou o número da placa
		
		if (Objects.nonNull(oVeiculoDTO)) {
			if (veiculoDTO.getCodVeiculo() == 0) {
				throw new ValidationException(Constants.VEICULO_PLACA_JA_CADASTRADA);
			} else {
				if (veiculoDTO.getCodVeiculo() != oVeiculoDTO.getCodVeiculo()) {
					throw new ValidationException(Constants.VEICULO_PLACA_JA_CADASTRADA);
				}
			}
		}
	}

	public void gravar(VeiculoDTO veiculoDTO) throws ValidationException, Exception {
		this.validarEntrada(veiculoDTO);
		this.veiculoRepository.save( new VeiculoEntity(veiculoDTO) );
	}
	
	public void deletar(int codVeiculo) throws ValidationException, Exception {
		this.pesquisarCodigo(codVeiculo); // Caso não encontre levanta uma ValidationException
		
		try {
			this.veiculoRepository.deleteById(codVeiculo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public VeiculoDTO pesquisarCodigo(int codVeiculo) throws ValidationException, Exception {
		try {
			return new VeiculoDTO( this.veiculoRepository.findById(codVeiculo).get() );
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.VEICULO_INEXISTENTE);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public VeiculoDTO pesuisarNroPlaca(String nroPlaca) throws ValidationException, Exception {
		try {
			return new VeiculoDTO( this.veiculoRepository.pesquisarNroPlaca(nroPlaca).get() );
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.VEICULO_PLACA_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
