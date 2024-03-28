package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import com.software.seguros.seguros.service.EstadoSiniestroService;
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
@RequestMapping("/api/v1/estado-siniestros")
public class EstadoSiniestroController {

    private final EstadoSiniestroService estadoSiniestroService;

    public EstadoSiniestroController(EstadoSiniestroService estadoSiniestroService) {
        this.estadoSiniestroService = estadoSiniestroService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEstadoSiniestro() {
        try{
            return ResponseEntity.ok().body(this.estadoSiniestroService.getEstadoSiniestros());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getEstadoSiniestroById(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.estadoSiniestroService.getEstadoSiniestroByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEstadoSiniestroById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.estadoSiniestroService.getEstadoSiniestroById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveEstadoSiniestro(@RequestBody EstadoSiniestro estadoSiniestro) {
        try{
            return ResponseEntity.ok().body(this.estadoSiniestroService.saveEstadoSiniestro(estadoSiniestro));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEstadoSiniestro(@PathVariable Integer id,
                                                   @RequestBody EstadoSiniestro estadoSiniestro) {
        try{
            String msg =
                    String.format("The EstadoSiniestro Id %s is different from the Url Id", estadoSiniestro.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoSiniestro.getId(), msg);
            return ResponseEntity.ok().body(this.estadoSiniestroService.updateEstadoSiniestro(estadoSiniestro));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEstadoSiniestro(@PathVariable Integer id, EstadoSiniestro estadoSiniestro) {
        try{
            String msg =
                    String.format("The EstadoSiniestro Id %s is different from the Url Id", estadoSiniestro.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoSiniestro.getId(), msg);
            this.estadoSiniestroService.deleteEstadoSiniestro(estadoSiniestro);
            return ResponseEntity.ok().body("Estado siniestro borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
