package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import com.software.seguros.seguros.service.EstadoPolizaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/estado-polizas")
public class EstadoPolizaController {

    private final EstadoPolizaService estadoPolizaService;

    public EstadoPolizaController(EstadoPolizaService estadoPolizaService) {
        this.estadoPolizaService = estadoPolizaService;
    }

    @GetMapping(value = "")
    public List<EstadoPoliza> getEstadoPoliza() {
        return this.estadoPolizaService.getEstadoPolizas();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public EstadoPoliza getEstadoPolizaByUuid(@PathVariable String uuid) {
        return this.estadoPolizaService.getEstadoPolizaByUuid(uuid);
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
    public EstadoPoliza saveEstadoPoliza(@RequestBody EstadoPoliza estadoPoliza) {
        return this.estadoPolizaService.saveEstadoPoliza(estadoPoliza);
    }

    @PutMapping(value = "/{id}")
    public EstadoPoliza updateEstadoPoliza(
            @PathVariable Integer id, @RequestBody EstadoPoliza estadoPoliza) {
        String msg =
                String.format("The EstadoPoliza Id %s is different from the Url Id", estadoPoliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoPoliza.getId(), msg);
        return this.estadoPolizaService.updateEstadoPoliza(estadoPoliza);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEstadoPoliza(@PathVariable Integer id, EstadoPoliza estadoPoliza) {
        String msg =
                String.format("The EstadoPoliza Id %s is different from the Url Id", estadoPoliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoPoliza.getId(), msg);
        this.estadoPolizaService.deleteEstadoPoliza(estadoPoliza);
    }
}
