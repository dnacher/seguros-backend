package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.service.CotizacionVendedorService;
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
@RequestMapping("/api/v1/cotizacion-vendedores")
public class CotizacionVendendorController {

    private final CotizacionVendedorService cotizacionVendedorService;

    public CotizacionVendendorController(CotizacionVendedorService cotizacionVendedorService) {
        this.cotizacionVendedorService = cotizacionVendedorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCotizacionVendedor() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cotizacionVendedorService.getCotizacionVendedores());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCotizacionVendedorById(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cotizacionVendedorService.getCotizacionVendedorByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCotizacionVendedorById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cotizacionVendedorService.getCotizacionVendedorById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/vendedor/{vendedorId}")
    public ResponseEntity<?> findByVendedor(@PathVariable Integer vendedorId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cotizacionVendedorService.findByVendedor(vendedorId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/producto/{productoId}")
    public ResponseEntity<?> findByProducto(@PathVariable Integer productoId) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cotizacionVendedorService.findByProducto(productoId));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCotizacionVendedor(@RequestBody CotizacionVendedor cotizacionVendedor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cotizacionVendedor.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.COTIZACION_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = cotizacionVendedorService.validarDatos(cotizacionVendedor);
            if(!codigo.equals(Codigo.OK)) {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }else {
                body.put("message", cotizacionVendedorService.saveCotizacionVendedor(cotizacionVendedor));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateCotizacionVendedor(@RequestBody CotizacionVendedor cotizacionVendedor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cotizacionVendedor.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.COTIZACION_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = cotizacionVendedorService.validarDatos(cotizacionVendedor);
            if(!codigo.equals(Codigo.OK)){
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            } else {
                body.put("message", cotizacionVendedorService.updateCotizacionVendedor(cotizacionVendedor));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCotizacionVendedor(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            cotizacionVendedorService.deleteCotizacionVendedor(id);
            return ResponseEntity.ok().body("Cotizacion borrada ID:" + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
