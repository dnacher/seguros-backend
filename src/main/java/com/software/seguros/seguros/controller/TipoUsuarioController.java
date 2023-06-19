package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.TipoUsuarioService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/tipo-usuarios")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getTipoUsuario() {
        try{
            return ResponseEntity.ok().body(this.tipoUsuarioService.getTipoUsuarios());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getTipoUsuarioByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.tipoUsuarioService.getTipoUsuarioByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTipoUsuarioById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.tipoUsuarioService.getTipoUsuarioById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try{
            return ResponseEntity.ok().body(this.tipoUsuarioService.saveTipoUsuario(tipoUsuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTipoUsuario(
            @PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario) {
        try{
            String msg = String.format("The TipoUsuario Id %s is different from the Url Id", tipoUsuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoUsuario.getId(), msg);
            return ResponseEntity.ok().body(this.tipoUsuarioService.updateTipoUsuario(tipoUsuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTipoUsuario(@PathVariable Integer id, TipoUsuario tipoUsuario) {
        try{
            String msg = String.format("The TipoUsuario Id %s is different from the Url Id", tipoUsuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoUsuario.getId(), msg);
            this.tipoUsuarioService.deleteTipoUsuario(tipoUsuario);
            return ResponseEntity.ok().body("Poliza borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
