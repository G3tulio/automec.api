package br.com.betuka.automec.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.ClienteDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.model.ClienteEntity;
import br.com.betuka.automec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	private ClienteDTO oClienteDTO;
	
	public List<ClienteDTO> listar() {
		List<ClienteEntity> lista = this.clienteRepository.findAll();
		return lista.stream().map(ClienteDTO::new).toList();
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
		
		try {
			oClienteDTO = this.pesquisarCpf(clienteDTO.getNroCpf());
		} catch (ValidationException e) {
			// Não encontrou o nome do usuário
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		if (Objects.nonNull(oClienteDTO)) {
			if (clienteDTO.getCodCliente() == 0) {
				throw new ValidationException(Constants.CLIENTE_CPF_CADASTRADA);
			}
			
			if ( (clienteDTO.getCodCliente() != 0) && (clienteDTO.getCodCliente() != oClienteDTO.getCodCliente()) ) {
				throw new ValidationException(Constants.CLIENTE_CPF_CADASTRADA);
			}
		}
	}
	
	public void adicionar(ClienteDTO clienteDTO) throws ValidationException, Exception {
		this.validarEntrada(clienteDTO);
		this.clienteRepository.save( new ClienteEntity(clienteDTO) );
	}
	
	public void atualizar(ClienteDTO clienteDTO) throws ValidationException, Exception {
		this.validarEntrada(clienteDTO);
		this.clienteRepository.save( new ClienteEntity(clienteDTO) );
	}
	
	public void deletar(int codCliente) throws ValidationException, Exception {
		this.pesquisarCodigo(codCliente);
		
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
	
	public List<ClienteDTO> pesquisarNome(String nomCliente) throws Exception {
		try {
			return ClienteDTO.toList(this.clienteRepository.pesquisarNome(nomCliente));
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
