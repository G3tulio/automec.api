package br.com.betuka.automec.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.VeiculoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.VeiculoEntity;
import br.com.betuka.automec.repository.VeiculoRepository;
import br.com.betuka.automec.service.tabApoio.ModeloService;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ModeloService modeloService;
	
	private VeiculoDTO oVeiculoDTO = null;
	
	public List<VeiculoDTO> listar() {
		List<VeiculoEntity> lista = this.veiculoRepository.findAll();
		return lista.stream().map(VeiculoDTO::new).toList();
	}
	
	private void validarEntrada(VeiculoDTO veiculoDTO) throws ValidationException, Exception {
		if (veiculoDTO.getCodVeiculo() != 0) {
			// Caso não encontre já levanta uma ValidationException
			this.pesquisarCodigo(veiculoDTO.getCodVeiculo()); 
		}
		
		// Caso não encontre já levanta uma ValidationException
		this.modeloService.pesquisarCodigo(veiculoDTO.getModelo().getCodModelo());
		
		if (veiculoDTO.getNroPlaca().isEmpty()) {
			throw new ValidationException(Constants.VEICULO_PLACA_OBRIGATORIA);
		}
		
		try {
			oVeiculoDTO = this.pesuisarNroPlaca(veiculoDTO.getNroPlaca());
		} catch (ValidationException e) {
			// Não encontrou o número da placa
			System.out.println("Não encontrou o número da placa");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oVeiculoDTO)) {
			if (veiculoDTO.getCodVeiculo() == 0) {
				throw new ValidationException(Constants.VEICULO_PLACA_CADASTRADA);
			}
			
			if ( (veiculoDTO.getCodVeiculo() != 0) && (veiculoDTO.getCodVeiculo() != oVeiculoDTO.getCodVeiculo()) ) {
				throw new ValidationException(Constants.VEICULO_PLACA_CADASTRADA);
			}
		}
	}

	public void adicionar(VeiculoDTO veiculoDTO) throws ValidationException, Exception {
		this.validarEntrada(veiculoDTO);
		this.veiculoRepository.save( new VeiculoEntity(veiculoDTO) );
	}
	
	public void atualizar(VeiculoDTO veiculoDTO) throws ValidationException, Exception {
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
