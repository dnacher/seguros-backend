package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.service.BancoService;
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
 * 2022-08-02
 */

@RestController
@RequestMapping("/api/v1/bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getBanco() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", bancoService.getBancos());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getBancoById(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", bancoService.getBancoByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/nombre/{nombre}")
    public ResponseEntity<?> getBancoByNombre(@PathVariable String nombre) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", bancoService.getByNombre(nombre));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBancoById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", bancoService.getBancoById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/count-by-nombre/{nombre}")
    public ResponseEntity<?> countBancoByNombre(@PathVariable String nombre) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", bancoService.countBancoByNombre(nombre));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveBanco(@RequestBody Banco banco) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(banco.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.BANCO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = bancoService.validarDatos(banco);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.bancoService.saveBanco(banco));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateBanco(@RequestBody Banco banco) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(banco.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.BANCO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = bancoService.validarDatos(banco);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.bancoService.updateBanco(banco));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBanco(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.bancoService.deleteBanco(id);
            return ResponseEntity.ok().body("Banco borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
