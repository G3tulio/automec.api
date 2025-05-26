package br.com.betuka.automec.controller.tabApoio;

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
import br.com.betuka.automec.dto.TabApoio.GrupoDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.service.tabApoio.GrupoService;

@RestController
@RequestMapping(value = "automec/grupo/")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	@GetMapping(value = "listar")
	public ResponseEntity<ResponseDTO<List<GrupoDTO>>> listar() {
		try {
			List<GrupoDTO> lista = this.grupoService.listar();
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                		                Constants.OCORREU_ERRO + e.getMessage()));
		}
	}
	
	@PostMapping(value = "adicionar")
	public ResponseEntity<ResponseDTO<Void>> adicionar(@RequestBody GrupoDTO grupoDTO) {
		try {
			this.grupoService.gravar(grupoDTO);
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
	public ResponseEntity<ResponseDTO<Void>> atualizar(@RequestBody GrupoDTO grupoDTO) {
		try {
			this.grupoService.gravar(grupoDTO);
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
	
	@DeleteMapping(value = "deletar/{codGrupo}")
	public ResponseEntity<ResponseDTO<Void>> deletar(@PathVariable("codGrupo") int pCodGrupo) {
    	try {
            this.grupoService.deletar(pCodGrupo);
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
	
	@GetMapping(value = "pesquisarCodigo/{codGrupo}")
	public ResponseEntity<ResponseDTO<GrupoDTO>> pesquisarCodigo(@PathVariable("codGrupo") int pCodGrupo) {
		try {
			GrupoDTO grupoDTO = this.grupoService.pesquisarCodigo(pCodGrupo);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, grupoDTO));
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
	                     
	@GetMapping(value = "pesquisarDescricao/{desGrupo}")
	public ResponseEntity<ResponseDTO<List<GrupoDTO>>> pesquisarDescricao(@PathVariable("desGrupo") String pDesGrupo) {
		try {
			List<GrupoDTO> lista = this.grupoService.pesquisarDescricao(pDesGrupo);
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