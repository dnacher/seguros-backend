package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getUsuario() {
        try{
            return ResponseEntity.ok().body(this.usuarioService.getUsuarios());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getUsuarioByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.usuarioService.getUsuarioByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.usuarioService.getUsuarioById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/nombre/{nombre}")
    public ResponseEntity<?> getUsuarioByNombre(@PathVariable String nombre) {
        try{
            return ResponseEntity.ok().body(this.usuarioService.getUsuarioByNombre(nombre));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok().body(this.usuarioService.saveUsuario(usuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id,
                                           @RequestBody Usuario usuario) {
        try{
            String msg = String.format("The Usuario Id %s is different from the Url Id", usuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, usuario.getId(), msg);
            return ResponseEntity.ok().body(this.usuarioService.updateUsuario(usuario));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id, Usuario usuario) {
        try{
            String msg = String.format("The Usuario Id %s is different from the Url Id", usuario.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, usuario.getId(), msg);
            this.usuarioService.deleteUsuario(usuario);
            return ResponseEntity.ok().body("Usuario borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
