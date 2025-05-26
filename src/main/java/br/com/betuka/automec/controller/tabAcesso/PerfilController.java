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
import br.com.betuka.automec.dto.tabAcesso.PerfilDTO;
import br.com.betuka.automec.exception.ValidationException;
import br.com.betuka.automec.service.tabAcesso.PerfilService;

@RestController
@RequestMapping(value = "automec/perfil/")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@GetMapping(value = "listar")
	public ResponseEntity<ResponseDTO<List<PerfilDTO>>> listar() {
		try {
			List<PerfilDTO> lista = this.perfilService.listar();
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, lista));
		} catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
		                		                Constants.OCORREU_ERRO + e.getMessage()));
	    }
	}

	@PostMapping(value = "adicionar")
	public ResponseEntity<ResponseDTO<Void>> adicionar(@RequestBody PerfilDTO perfilDTO) {
		try {
			this.perfilService.gravar(perfilDTO);
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
	public ResponseEntity<ResponseDTO<Void>> atualizar(@RequestBody PerfilDTO perfilDTO) {
		try {
			this.perfilService.gravar(perfilDTO);
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

    @DeleteMapping(value = "deletar/{codPerfil}")
    public ResponseEntity<ResponseDTO<Void>> deletar(@PathVariable("codPerfil") int pCodPerfil ) {
    	try {
            this.perfilService.deletar(pCodPerfil);
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

    @GetMapping(value = "pesquisarCodigo/{codPerfil}")
    public ResponseEntity<ResponseDTO<PerfilDTO>> pesquisarCodigo(@PathVariable("codPerfil") int pCodPerfil ) {
		try {
			PerfilDTO perfilDTO = this.perfilService.pesquisarCodigo(pCodPerfil);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, perfilDTO));
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

    @GetMapping(value = "pesquisarDescricao/{desPerfil}")
    public ResponseEntity<ResponseDTO<PerfilDTO>> pesquisarDescricao(@PathVariable("desPerfil") String pDesPerfil ) {
		try {
			PerfilDTO perfilDTO = this.perfilService.pesquisarDescricao(pDesPerfil);
			return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), Constants.EXECUTADO_COM_SUCESSO, perfilDTO));
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

    @GetMapping(value = "pesquisarPorDescricao/{desPerfil}")
    public ResponseEntity<ResponseDTO<List<PerfilDTO>>> pesquisarPorDescricao(@PathVariable("desPerfil") String pDesPerfil ) {
		try {
			List<PerfilDTO> lista = this.perfilService.pesquisarPorDescricao(pDesPerfil);
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