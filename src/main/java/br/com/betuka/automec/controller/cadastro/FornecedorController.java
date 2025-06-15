package br.com.betuka.automec.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.betuka.automec.constant.Constants;
import br.com.betuka.automec.dto.ResponseDTO;
import br.com.betuka.automec.dto.cadastro.FornecedorDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.service.cadastro.FornecedorService;

@RestController
@RequestMapping(value = "automec/fornecedor/")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "listar")
	public ResponseEntity<ResponseDTO<List<FornecedorDTO>>> listar() {
		try {
			List<FornecedorDTO> lista = this.fornecedorService.listar();
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@PostMapping(value = "adicionar")
	public ResponseEntity<ResponseDTO<Void>> adicionar(@RequestBody FornecedorDTO FornecedorDTO) {
		try {
			this.fornecedorService.gravar(FornecedorDTO);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO));
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                e.getMessage()));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@PutMapping(value = "atualizar")
	public ResponseEntity<ResponseDTO<Void>> atualizar(@RequestBody FornecedorDTO FornecedorDTO) {
		try {
			this.fornecedorService.gravar(FornecedorDTO);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO));
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                e.getMessage()));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@DeleteMapping(value = "deletar/{codFornecedor}")
	public ResponseEntity<ResponseDTO<Void>> deletar(@PathVariable("codFornecedor") int pCodFornecedor) {
    	try {
            this.fornecedorService.deletar(pCodFornecedor);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO));
		} catch (ValidationException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                e.getMessage()));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@GetMapping(value = "pesquisarCodigo/{codFornecedor}")
	public ResponseEntity<ResponseDTO<FornecedorDTO>> pesquisarCodigo(@PathVariable("codFornecedor") int pCodFornecedor) {
		try {
			FornecedorDTO fornecedorDTO = this.fornecedorService.pesquisarCodigo(pCodFornecedor);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, fornecedorDTO));
		} catch (ValidationException e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
		                		                e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}
	                     
	@GetMapping(value = "pesquisarDescricao/{desFornecedor}")
	public ResponseEntity<ResponseDTO<FornecedorDTO>> pesquisarDescricao(@PathVariable("desFornecedor") String pDesFornecedor) {
		try {
			FornecedorDTO fornecedorDTO = this.fornecedorService.pesquisarDescricao(pDesFornecedor);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, fornecedorDTO));
		} catch (ValidationException e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
		                		                e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}
	
	@GetMapping(value = "buscarDescricao/{desFornecedor}")
	public ResponseEntity<ResponseDTO<List<FornecedorDTO>>> buscarDescricao(@PathVariable("desFornecedor") String pDesFornecedor) {
		try {
			List<FornecedorDTO> lista = this.fornecedorService.buscarDescricao(pDesFornecedor);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (ValidationException e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
		                		                e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}
}