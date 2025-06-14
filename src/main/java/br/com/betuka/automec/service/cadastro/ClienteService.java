package br.com.betuka.automec.service.cadastro;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.cadastro.ClienteDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.cadastro.ClienteEntity;
import br.com.betuka.automec.repository.cadastro.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteDTO> listar() {
		return ClienteDTO.toList(this.clienteRepository.findAll());
	}
	
	private void validarEntrada(ClienteDTO clienteDTO) throws ValidationException, Exception {
		if (clienteDTO.getCodCliente() != 0) {
			this.pesquisarCodigo(clienteDTO.getCodCliente());
		}
		
		if (clienteDTO.getNomCliente().isBlank()) {
			throw new ValidationException(Constants.CLIENTE_NOME_OBRIGATORIA);
		}
		
		if (clienteDTO.getNroCpf().isBlank()) {
			throw new ValidationException(Constants.CLIENTE_CPF_OBRIGATORIA);
		}
		
		if (clienteDTO.getNroCelular().isBlank()) {
			throw new ValidationException(Constants.CLIENTE_CELULAR_OBRIGATORIA);
		}
		
		ClienteDTO oClienteDTO = null;
		
		try {
			oClienteDTO = this.pesquisarCpf(clienteDTO.getNroCpf());
		} catch (ValidationException e) {
			System.out.println("Não encontrou o cpf do cliente"); 
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oClienteDTO)) {
			if (clienteDTO.getCodCliente() == 0) {
				throw new ValidationException(Constants.CLIENTE_CPF_JA_CADASTRADA);
			} else {
				if (clienteDTO.getCodCliente() != oClienteDTO.getCodCliente()) {
					throw new ValidationException(Constants.CLIENTE_CPF_JA_CADASTRADA);
				} 
			}
		}
	}
	
	public void gravar(ClienteDTO clienteDTO) throws ValidationException, Exception {
		this.validarEntrada(clienteDTO);
		this.clienteRepository.save( new ClienteEntity(clienteDTO) );
	}
	
	public void deletar(int codCliente) throws ValidationException, Exception {
		this.pesquisarCodigo(codCliente);
		
		// Mas a frente, verificar se o cliente está relacionado com a entidade serviço [ FK ]
		
		try {
			this.clienteRepository.deleteById(codCliente);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ClienteDTO pesquisarCodigo(int codCliente) throws ValidationException, Exception {
		try {
			return new ClienteDTO(this.clienteRepository.findById(codCliente).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.CLIENTE_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<ClienteDTO> buscarNome(String nomCliente) throws Exception {
		try {
			return ClienteDTO.toList(this.clienteRepository.buscarNome(nomCliente));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public ClienteDTO pesquisarCpf(String nroCPF) throws ValidationException, Exception {
		try {
			return new ClienteDTO(this.clienteRepository.pesquisarCpf(nroCPF).get());
		} catch (NoSuchElementException e) {
			throw new ValidationException(Constants.CLIENTE_CPF_INEXISTENTE);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
