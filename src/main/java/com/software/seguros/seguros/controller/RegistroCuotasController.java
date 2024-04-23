package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import com.software.seguros.seguros.service.RegistroCuotasService;
import com.software.seguros.seguros.utils.UtilsGeneral;
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
@RequestMapping("/api/v1/registro-cuotas")
public class RegistroCuotasController {

    private final RegistroCuotasService registroCuotasService;

    public RegistroCuotasController(RegistroCuotasService registroCuotasService) {
        this.registroCuotasService = registroCuotasService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getRegistroCuotas() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", registroCuotasService.getRegistroCuotas());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getRegistroCuotasByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", registroCuotasService.getRegistroCuotasByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRegistroCuotasById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", registroCuotasService.getRegistroCuotasById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/cuotas")
    public ResponseEntity<?> getRegistrosCuotasConCuotas() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", registroCuotasService.getRegistrosCuotasConCuotas());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveRegistroCuotas(@RequestBody RegistroCuotas registroCuotas) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(registroCuotas.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.REGISTRO_CUOTAS_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = registroCuotasService.validarDatos(registroCuotas);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", registroCuotasService.saveRegistroCuotas(registroCuotas));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateRegistroCuotas(@RequestBody RegistroCuotas registroCuotas) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(registroCuotas.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.REGISTRO_CUOTAS_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = registroCuotasService.validarDatos(registroCuotas);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(registroCuotasService.updateRegistroCuotas(registroCuotas));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRegistroCuotas(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            registroCuotasService.deleteRegistroCuotas(id);
            return ResponseEntity.ok().body("Banco borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
