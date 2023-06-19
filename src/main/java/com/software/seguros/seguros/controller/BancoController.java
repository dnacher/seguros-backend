package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.service.BancoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try{
            return ResponseEntity.ok().body(this.bancoService.getBancos());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getBancoById(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.bancoService.getBancoByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/nombre/{nombre}")
    public ResponseEntity<?> getBancoByNombre(@PathVariable String nombre) {
        try{
            return ResponseEntity.ok().body(this.bancoService.getByNombre(nombre));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBancoById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.bancoService.getBancoById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/count-by-nombre/{nombre}")
    public ResponseEntity<?> countBancoByNombre(@PathVariable String nombre) {
        try{
            return ResponseEntity.ok().body(this.bancoService.countBancoByNombre(nombre));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveBanco(@RequestBody Banco banco) {
        try{
            return ResponseEntity.ok().body(this.bancoService.saveBanco(banco));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBanco(
            @PathVariable Integer id, @RequestBody Banco banco) {
        try{
            String msg = "El id del banco es diferente al de la URL " + banco.getId();
            UtilsGeneral.validateUrlIdEqualsBodyId(id, banco.getId(), msg);
            return ResponseEntity.ok().body(this.bancoService.updateBanco(banco));
        } catch (SegurosException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST_400).body(ex.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBanco(@PathVariable Integer id, Banco banco) {
        try{
            String msg =
                    String.format("El id del banco es diferente al de la URL %s", banco.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, banco.getId(), msg);
            this.bancoService.deleteBanco(banco);
            return ResponseEntity.ok().body("Banco borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
