package br.com.betuka.automec.controller.tabela.apoio;

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
import br.com.betuka.automec.dto.tabela.apoio.CategoriaDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.service.tabela.apoio.CategoriaService;

@RestController
@RequestMapping(value = "automec/categoria/")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(value = "listar")
	public ResponseEntity<ResponseDTO<List<CategoriaDTO>>> listar() {
		try {
			List<CategoriaDTO> lista = this.categoriaService.listar();
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@PostMapping(value = "adicionar")
	public ResponseEntity<ResponseDTO<Void>> adicionar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			this.categoriaService.gravar(categoriaDTO);
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
	public ResponseEntity<ResponseDTO<Void>> atualizar(@RequestBody CategoriaDTO categoriaDTO) {
		try {
			this.categoriaService.gravar(categoriaDTO);
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
	
	@DeleteMapping(value = "deletar/{codCategoria}")
	public ResponseEntity<ResponseDTO<Void>> deletar(@PathVariable("codCategoria") int pCodCategoria) {
    	try {
            this.categoriaService.deletar(pCodCategoria);
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
	
	@GetMapping(value = "pesquisarCodigo/{codCategoria}")
	public ResponseEntity<ResponseDTO<CategoriaDTO>> pesquisarCodigo(@PathVariable("codCategoria") int pCodCategoria) {
		try {
			CategoriaDTO categoriaDTO = this.categoriaService.pesquisarCodigo(pCodCategoria);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, categoriaDTO));
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
	                     
	@GetMapping(value = "pesquisarDescricao/{desCategoria}")
	public ResponseEntity<ResponseDTO<CategoriaDTO>> pesquisarDescricao(@PathVariable("desCategoria") String pDesCategoria) {
		try {
			CategoriaDTO categoriaDTO = this.categoriaService.pesquisarDescricao(pDesCategoria);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, categoriaDTO));
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
	    
	@GetMapping(value = "buscarDescricao/{desCategoria}")
	public ResponseEntity<ResponseDTO<List<CategoriaDTO>>> buscarDescricao(@PathVariable("desCategoria") String pDesCategoria) {
		try {
			List<CategoriaDTO> lista = this.categoriaService.buscarDescricao(pDesCategoria);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}
}