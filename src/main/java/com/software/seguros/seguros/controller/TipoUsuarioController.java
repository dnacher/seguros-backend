package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.TipoUsuarioService;
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
@RequestMapping("/api/v1/tipo-usuarios")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getTipoUsuario() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", tipoUsuarioService.getTipoUsuarios());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getTipoUsuarioByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", tipoUsuarioService.getTipoUsuarioByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTipoUsuarioById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            TipoUsuario tipoUsuario = tipoUsuarioService.getTipoUsuarioById(id);
            body.put("message", tipoUsuario);
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(tipoUsuario.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.TIPO_USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = tipoUsuarioService.validarDatos(tipoUsuario);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", tipoUsuarioService.saveTipoUsuario(tipoUsuario));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(tipoUsuario.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.TIPO_USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = tipoUsuarioService.validarDatos(tipoUsuario);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(tipoUsuarioService.updateTipoUsuario(tipoUsuario));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTipoUsuario(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            tipoUsuarioService.deleteTipoUsuario(id);
            return ResponseEntity.ok().body("Tipo de usuario borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
