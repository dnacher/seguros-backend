package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.service.UsuarioService;
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

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", usuarioService.getUsuarios());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getUsuarioByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", usuarioService.getUsuarioByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", usuarioService.getUsuarioById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/nombre/{nombre}")
    public ResponseEntity<?> getUsuarioByNombre(@PathVariable String nombre) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", usuarioService.getUsuarioByNombre(nombre));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(usuario.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = usuarioService.validarDatos(usuario);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", usuarioService.saveUsuario(usuario));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(usuario.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = usuarioService.validarDatos(usuario);
            if(Codigo.OK.equals(codigo)) {
                Usuario usuarioBD = usuarioService.getUsuarioById(usuario.getId());
                usuario.setPassword(usuarioBD.getPassword());
                body.put("message", usuarioService.updateUsuario(usuario));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok().body("Usuario borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
