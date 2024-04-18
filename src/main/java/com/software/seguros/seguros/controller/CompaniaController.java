package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.service.CompaniaService;
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
@RequestMapping("/api/v1/companias")
public class CompaniaController {

    private final CompaniaService companiaService;


    public CompaniaController(CompaniaService companiaService) {
        this.companiaService = companiaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCompania() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", this.companiaService.getCompanias());
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCompaniaByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", this.companiaService.getCompaniaByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCompaniaById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", this.companiaService.getCompaniaById(id));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCompania(@RequestBody Compania compania) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(compania.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.COMPANIA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = companiaService.validarDatos(compania);
            if(!codigo.equals(Codigo.OK)){
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            } else {
                body.put("message", this.companiaService.saveCompania(compania));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            }
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateCompania(@RequestBody Compania compania) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(compania.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.COMPANIA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = companiaService.validarDatos(compania);
            if(!codigo.equals(Codigo.OK)) {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }else {
                body.put("message", this.companiaService.updateCompania(compania));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            }
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCompania(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.companiaService.deleteCompania(id);
            return ResponseEntity.ok().body("Compañia borrada ID:" + id);
        } catch(SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
