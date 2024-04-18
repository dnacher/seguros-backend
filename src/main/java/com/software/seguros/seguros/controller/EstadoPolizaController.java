package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import com.software.seguros.seguros.service.EstadoPolizaService;
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
@RequestMapping("/api/v1/estado-polizas")
public class EstadoPolizaController {

    private final EstadoPolizaService estadoPolizaService;

    public EstadoPolizaController(EstadoPolizaService estadoPolizaService) {
        this.estadoPolizaService = estadoPolizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEstadoPoliza() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoPolizaService.getEstadoPolizas());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getEstadoPolizaByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoPolizaService.getEstadoPolizaByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEstadoPolizaById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", estadoPolizaService.getEstadoPolizaById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveEstadoPoliza(@RequestBody EstadoPoliza estadoPoliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(estadoPoliza.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.ESTADO_POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = estadoPolizaService.validarDatos(estadoPoliza);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", estadoPolizaService.saveEstadoPoliza(estadoPoliza));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateEstadoPoliza(@RequestBody EstadoPoliza estadoPoliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(estadoPoliza.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.ESTADO_POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = estadoPolizaService.validarDatos(estadoPoliza);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(estadoPolizaService.updateEstadoPoliza(estadoPoliza));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEstadoPoliza(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            estadoPolizaService.deleteEstadoPoliza(id);
            return ResponseEntity.ok().body("Estado poliza borrada ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
