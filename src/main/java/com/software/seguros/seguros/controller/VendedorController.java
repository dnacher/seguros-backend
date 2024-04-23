package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.service.VendedorService;
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
@RequestMapping("/api/v1/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getVendedor() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", vendedorService.getVendedores());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getVendedorByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", vendedorService.getVendedorByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getVendedorById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", vendedorService.getVendedorById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveVendedor(@RequestBody Vendedor vendedor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(vendedor.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.VENDEDOR_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = vendedorService.validarDatos(vendedor);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", vendedorService.saveVendedor(vendedor));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateVendedor(@RequestBody Vendedor vendedor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(vendedor.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.VENDEDOR_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = vendedorService.validarDatos(vendedor);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(vendedorService.updateVendedor(vendedor));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVendedor(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            vendedorService.deleteVendedor(id);
            return ResponseEntity.ok().body("Vendedor borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
