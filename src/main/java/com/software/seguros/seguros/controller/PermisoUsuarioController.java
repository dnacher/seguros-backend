package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.PermisoUsuarioService;
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
@RequestMapping("/api/v1/permiso-usuarios")
public class PermisoUsuarioController {

    private final PermisoUsuarioService permisoUsuarioService;

    public PermisoUsuarioController(PermisoUsuarioService permisoUsuarioService) {
        this.permisoUsuarioService = permisoUsuarioService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPermisoUsuario() {
        try{
            return ResponseEntity.ok().body(this.permisoUsuarioService.getPermisoUsuarios());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getPermisoUsuarioByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.permisoUsuarioService.getPermisoUsuarioByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPermisoUsuarioById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.permisoUsuarioService.getPermisoUsuarioById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/tipo-usuario")
    public ResponseEntity<?> findAllByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try{
            return ResponseEntity.ok().body(this.permisoUsuarioService.findAllByTipoUsuario(tipoUsuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> savePermisoUsuario(@RequestBody PermisoUsuario permisoUsuario) {
        try{
            return ResponseEntity.ok().body(this.permisoUsuarioService.savePermisoUsuario(permisoUsuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePermisoUsuario(@PathVariable Integer id,
                                                  @RequestBody PermisoUsuario permisoUsuario) {
        try{
            String msg = String.format("The PermisoUsuario Id %s is different from the Url Id", permisoUsuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, permisoUsuario.getId(), msg);
            return ResponseEntity.ok().body(this.permisoUsuarioService.updatePermisoUsuario(permisoUsuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePermisoUsuario(@PathVariable Integer id, PermisoUsuario permisoUsuario) {
        try{
            String msg =
                    String.format("The PermisoUsuario Id %s is different from the Url Id", permisoUsuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, permisoUsuario.getId(), msg);
            this.permisoUsuarioService.deletePermisoUsuario(permisoUsuario);
            return ResponseEntity.ok().body("Permiso borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }


    }

    @DeleteMapping(value = "/tipo-usuario")
    public ResponseEntity<?> deleteByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try{
            this.permisoUsuarioService.deleteByTipoUsuario(tipoUsuario);
            return ResponseEntity.ok().body("Permiso borrado tipo Usuario:" + tipoUsuario.getNombre());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
