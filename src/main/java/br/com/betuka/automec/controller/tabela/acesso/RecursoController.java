package br.com.betuka.automec.controller.tabAcesso;

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
import br.com.betuka.automec.dto.tabAcesso.RecursoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.service.tabAcesso.RecursoService;

@RestController
@RequestMapping(value = "automec/recurso/")
public class RecursoController {
	
	@Autowired
	public RecursoService recursoService;
	
	@GetMapping(value = "listar")
	public ResponseEntity<ResponseDTO<List<RecursoDTO>>> listar() {
		try {
			List<RecursoDTO> lista = this.recursoService.listar();
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
		                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}

	@PostMapping(value = "adicionar")
	public ResponseEntity<ResponseDTO<Void>> adicionar(@RequestBody RecursoDTO recursoDTO) {
		try {
			this.recursoService.gravar(recursoDTO);
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
	public ResponseEntity<ResponseDTO<Void>> atualizar(@RequestBody RecursoDTO recursoDTO) {
		try {
			this.recursoService.gravar(recursoDTO);
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

    @DeleteMapping(value = "deletar/{codRecurso}")
    public ResponseEntity<ResponseDTO<Void>> deletar(@PathVariable("codRecurso") int pCodRecurso ) {
    	try {
            this.recursoService.deletar(pCodRecurso);
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

    @GetMapping(value = "pesquisarCodigo/{codRecurso}")
    public ResponseEntity<ResponseDTO<RecursoDTO>> pesquisarCodigo(@PathVariable("codRecurso") int pCodRecurso ) {
		try {
			RecursoDTO recursoDTO = this.recursoService.pesquisarCodigo(pCodRecurso);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, recursoDTO));
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

    @GetMapping(value = "pesquisarDescricao/{desRecurso}")
    public ResponseEntity<ResponseDTO<RecursoDTO>> pesquisarDescricao(@PathVariable("desRecurso") String pDesRecurso ) {
		try {
			RecursoDTO recursoDTO = this.recursoService.pesquisarDescricao(pDesRecurso);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, recursoDTO));
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

    @GetMapping(value = "buscarDescricao/{desRecurso}")
    public ResponseEntity<ResponseDTO<List<RecursoDTO>>> buscarDescricao(@PathVariable("desRecurso") String pDesRecurso ) {
		try {
			List<RecursoDTO> lista = this.recursoService.buscarDescricao(pDesRecurso);
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
