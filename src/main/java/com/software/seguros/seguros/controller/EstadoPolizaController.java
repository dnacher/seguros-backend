package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import com.software.seguros.seguros.service.EstadoPolizaService;
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

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/estado-polizas")
public class EstadoPolizaController {

    private final EstadoPolizaService estadoPolizaService;

    public EstadoPolizaController(EstadoPolizaService estadoPolizaService) {
        this.estadoPolizaService = estadoPolizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEstadoPoliza() {
        try{
            return ResponseEntity.ok().body(this.estadoPolizaService.getEstadoPolizas());
        } catch (Exception ex){
            return ResponseEntity.status(org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getEstadoPolizaByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.estadoPolizaService.getEstadoPolizaByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEstadoPolizaById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(this.estadoPolizaService.getEstadoPolizaById(id));
        }catch ( SegurosException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveEstadoPoliza(@RequestBody EstadoPoliza estadoPoliza) {
        try{
            return ResponseEntity.ok().body(this.estadoPolizaService.saveEstadoPoliza(estadoPoliza));
        } catch (Exception ex){
            return ResponseEntity.status(org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEstadoPoliza(
            @PathVariable Integer id, @RequestBody EstadoPoliza estadoPoliza) {
        try{
            String msg = String.format("The EstadoPoliza Id %s is different from the Url Id", estadoPoliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoPoliza.getId(), msg);
            return ResponseEntity.ok().body(this.estadoPolizaService.updateEstadoPoliza(estadoPoliza));
        } catch (Exception ex){
            return ResponseEntity.status(org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEstadoPoliza(@PathVariable Integer id, EstadoPoliza estadoPoliza) {
        try{
            String msg = String.format("The EstadoPoliza Id %s is different from the Url Id", estadoPoliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoPoliza.getId(), msg);
            this.estadoPolizaService.deleteEstadoPoliza(estadoPoliza);
            return ResponseEntity.ok().body("Estado poliza borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
