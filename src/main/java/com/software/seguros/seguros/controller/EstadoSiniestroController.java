package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import com.software.seguros.seguros.service.EstadoSiniestroService;
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
@RequestMapping("/api/v1/estado-siniestros")
public class EstadoSiniestroController {

    private final EstadoSiniestroService estadoSiniestroService;

    public EstadoSiniestroController(EstadoSiniestroService estadoSiniestroService) {
        this.estadoSiniestroService = estadoSiniestroService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEstadoSiniestro() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoSiniestroService.getEstadoSiniestros());
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getEstadoSiniestroById(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoSiniestroService.getEstadoSiniestroByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEstadoSiniestroById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoSiniestroService.getEstadoSiniestroById(id));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveEstadoSiniestro(@RequestBody EstadoSiniestro estadoSiniestro) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(estadoSiniestro.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.ESTADO_SINIESTRO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = estadoSiniestroService.validarDatos(estadoSiniestro);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", estadoSiniestroService.saveEstadoSiniestro(estadoSiniestro));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEstadoSiniestro(@RequestBody EstadoSiniestro estadoSiniestro) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(estadoSiniestro.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.ESTADO_SINIESTRO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = estadoSiniestroService.validarDatos(estadoSiniestro);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(estadoSiniestroService.updateEstadoSiniestro(estadoSiniestro));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEstadoSiniestro(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            estadoSiniestroService.deleteEstadoSiniestro(id);
            return ResponseEntity.ok().body("Estado siniestro borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
