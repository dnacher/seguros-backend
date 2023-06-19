package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import com.software.seguros.seguros.service.SiniestroService;
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
        try{
            return ResponseEntity.ok().body(this.siniestroService.getSiniestros());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getSiniestroByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.siniestroService.getSiniestroByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSiniestroById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.siniestroService.getSiniestroById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/poliza")
    public ResponseEntity<?> findByPoliza(@RequestBody Poliza poliza) {
        try{
            return ResponseEntity.ok().body(this.siniestroService.findByPoliza(poliza));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<?> findByCliente(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok().body(this.siniestroService.findByCliente(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveSiniestro(@RequestBody Siniestro siniestro) {
        try{
            return ResponseEntity.ok().body(this.siniestroService.saveSiniestro(siniestro));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateSiniestro(
            @PathVariable Integer id, @RequestBody Siniestro siniestro) {
        try{
            String msg = String.format("The Siniestro Id %s is different from the Url Id", siniestro.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, siniestro.getId(), msg);
            return ResponseEntity.ok().body(this.siniestroService.updateSiniestro(siniestro));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSiniestro(@PathVariable Integer id, Siniestro siniestro) {
        try{
            String msg = String.format("The Siniestro Id %s is different from the Url Id", siniestro.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, siniestro.getId(), msg);
            this.siniestroService.deleteSiniestro(siniestro);
            return ResponseEntity.ok().body("Siniestro borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
