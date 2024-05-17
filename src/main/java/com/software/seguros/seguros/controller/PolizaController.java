package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.service.PolizaService;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPoliza() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizas());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getPolizaByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizaByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPolizaById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizaById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/premio/{desde}/{hasta}")
    public ResponseEntity<?> getTotalPremioByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                    @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getTotalPremioByFechasGroupByProductos(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/prima/{desde}/{hasta}")
    public ResponseEntity<?> getTotalPrimaByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                   @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getTotalPrimaByFechasGroupByProductos(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/count/{desde}/{hasta}")
    public ResponseEntity<?> getCountProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                               @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getCountProductos(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date desde,
                                               @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizasByFecha(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/vencimiento/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasVencimientoByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                     @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizasVencimientoByFecha(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/sum-prima/{desde}/{hasta}")
    public ResponseEntity<?> getSUMPrimaProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                  @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getSUMPrimaProductos(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/comisiones/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasComisionesByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                         @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.getPolizasComisionesByFecha(desde, hasta));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/cliente/{clienteId}")
    public ResponseEntity<?> findByCliente(@PathVariable Integer clienteId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.findByCliente(clienteId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/cliente-nuevo-renovacion/{clienteId}")
    public ResponseEntity<?> findByClienteNuevoYRenovacion(@PathVariable Integer clienteId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.findByClienteNuevoYRenovacion(clienteId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/cliente-endoso/{clienteId}")
    public ResponseEntity<?> findByClienteAndEstadoEndoso(@PathVariable Integer clienteId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", polizaService.findByClienteAndEstadoEndoso(clienteId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> savePoliza(@RequestBody Poliza poliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(poliza.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = polizaService.validarDatos(poliza);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", polizaService.savePoliza(poliza));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updatePoliza(@RequestBody Poliza poliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(poliza.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = polizaService.validarDatos(poliza);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(polizaService.updatePoliza(poliza));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePoliza(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            polizaService.deletePoliza(id);
            return ResponseEntity.ok().body("Poliza borrada ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
