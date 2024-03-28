package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Moneda;
import com.software.seguros.seguros.service.MonedaService;
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
@RequestMapping("/api/v1/monedas")
public class MonedaController {

    private final MonedaService monedaService;

    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getMoneda() {
        try{
            return ResponseEntity.ok().body(this.monedaService.getMonedas());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getMonedaByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.monedaService.getMonedaByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMonedaById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.monedaService.getMonedaById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveMoneda(@RequestBody Moneda moneda) {
        try{
            return ResponseEntity.ok().body(this.monedaService.saveMoneda(moneda));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateMoneda(
            @PathVariable Integer id, @RequestBody Moneda moneda) {
        try{
            String msg = String.format("The Moneda Id %s is different from the Url Id", moneda.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, moneda.getId(), msg);
            return ResponseEntity.ok().body(this.monedaService.updateMoneda(moneda));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMoneda(@PathVariable Integer id, Moneda moneda) {
        try{
            String msg = String.format("The Moneda Id %s is different from the Url Id", moneda.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, moneda.getId(), msg);
            this.monedaService.deleteMoneda(moneda);
            return ResponseEntity.ok().body("Moneda borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
