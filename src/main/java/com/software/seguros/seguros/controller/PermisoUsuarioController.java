package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.PermisoUsuarioService;
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
@RequestMapping("/api/v1/permiso-usuarios")
public class PermisoUsuarioController {

    private final PermisoUsuarioService permisoUsuarioService;

    public PermisoUsuarioController(PermisoUsuarioService permisoUsuarioService) {
        this.permisoUsuarioService = permisoUsuarioService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPermisoUsuario() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", permisoUsuarioService.getPermisoUsuarios());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getPermisoUsuarioByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", permisoUsuarioService.getPermisoUsuarioByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPermisoUsuarioById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", permisoUsuarioService.getPermisoUsuarioById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "/tipo-usuario")
    public ResponseEntity<?> findAllByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", permisoUsuarioService.findAllByTipoUsuario(tipoUsuario));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> savePermisoUsuario(@RequestBody PermisoUsuario permisoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(permisoUsuario.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERMISO_USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = permisoUsuarioService.validarDatos(permisoUsuario);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", permisoUsuarioService.savePermisoUsuario(permisoUsuario));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updatePermisoUsuario(@RequestBody PermisoUsuario permisoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(permisoUsuario.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERMISO_USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = permisoUsuarioService.validarDatos(permisoUsuario);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(permisoUsuarioService.updatePermisoUsuario(permisoUsuario));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePermisoUsuario(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            permisoUsuarioService.deletePermisoUsuario(id);
            return ResponseEntity.ok().body("Permiso usuario borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/tipo-usuario")
    public ResponseEntity<?> deleteByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            permisoUsuarioService.deleteByTipoUsuario(tipoUsuario);
            return ResponseEntity.ok().body("Permiso usuario borrado tipo usuario ID: " + tipoUsuario.getId());
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
