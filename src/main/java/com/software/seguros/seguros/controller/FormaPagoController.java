package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.service.FormaPagoService;
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
@RequestMapping("/api/v1/forma-pagos")
public class FormaPagoController {

    private final FormaPagoService formaPagoService;

    public FormaPagoController(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getFormaPago() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", formaPagoService.getFormaPagos());
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getFormaPagoByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", formaPagoService.getFormaPagoByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getFormaPagoById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", formaPagoService.getFormaPagoById(id));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveFormaPago(@RequestBody FormaPago formaPago) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(formaPago.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.FORMA_PAGO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = formaPagoService.validarDatos(formaPago);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", formaPagoService.saveFormaPago(formaPago));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateFormaPago(@RequestBody FormaPago formaPago) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(formaPago.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.FORMA_PAGO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = formaPagoService.validarDatos(formaPago);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(formaPagoService.updateFormaPago(formaPago));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFormaPago(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            formaPagoService.deleteFormaPago(id);
            return ResponseEntity.ok().body("Forma pago borrada ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
