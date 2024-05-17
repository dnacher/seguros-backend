package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Siniestro;
import com.software.seguros.seguros.service.SiniestroService;
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
@RequestMapping("/api/v1/siniestros")
public class SiniestrosController {

    private final SiniestroService siniestroService;

    public SiniestrosController(SiniestroService siniestroService) {
        this.siniestroService = siniestroService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getSiniestro() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", siniestroService.getSiniestros());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getSiniestroByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", siniestroService.getSiniestroByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSiniestroById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", siniestroService.getSiniestroById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/poliza/{polizaId}")
    public ResponseEntity<?> findByPoliza(@PathVariable Integer polizaId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", siniestroService.findByPoliza(polizaId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/cliente/{clienteId}")
    public ResponseEntity<?> findByCliente(@PathVariable Integer clienteId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", siniestroService.findByCliente(clienteId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveSiniestro(@RequestBody Siniestro siniestro) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(siniestro.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.SINIESTRO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = siniestroService.validarDatos(siniestro);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", siniestroService.saveSiniestro(siniestro));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateSiniestro(@RequestBody Siniestro siniestro) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(siniestro.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.SINIESTRO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = siniestroService.validarDatos(siniestro);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(siniestroService.updateSiniestro(siniestro));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSiniestro(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            siniestroService.deleteSiniestro(id);
            return ResponseEntity.ok().body("Siniestro borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
