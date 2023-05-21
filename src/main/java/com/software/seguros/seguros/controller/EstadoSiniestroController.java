package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import com.software.seguros.seguros.service.EstadoSiniestroService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/estado-siniestros")
public class EstadoSiniestroController {

    private final EstadoSiniestroService estadoSiniestroService;

    public EstadoSiniestroController(EstadoSiniestroService estadoSiniestroService) {
        this.estadoSiniestroService = estadoSiniestroService;
    }

    @GetMapping(value = "/")
    public List<EstadoSiniestro> getEstadoSiniestro() {
        return this.estadoSiniestroService.getEstadoSiniestros();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public EstadoSiniestro getEstadoSiniestroById(@PathVariable String uuid) {
        return this.estadoSiniestroService.getEstadoSiniestroByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public EstadoSiniestro getEstadoSiniestroById(@PathVariable Integer id) {
        return this.estadoSiniestroService.getEstadoSiniestroById(id);
    }

    @PostMapping(value = "/")
    public EstadoSiniestro saveEstadoSiniestro(@RequestBody EstadoSiniestro estadoSiniestro) {
        return this.estadoSiniestroService.saveEstadoSiniestro(estadoSiniestro);
    }

    @PutMapping(value = "/{id}")
    public EstadoSiniestro updateEstadoSiniestro(
            @PathVariable Integer id, @RequestBody EstadoSiniestro estadoSiniestro) {
        String msg =
                String.format("The EstadoSiniestro Id %s is different from the Url Id", estadoSiniestro.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoSiniestro.getId(), msg);
        return this.estadoSiniestroService.updateEstadoSiniestro(estadoSiniestro);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEstadoSiniestro(@PathVariable Integer id, EstadoSiniestro estadoSiniestro) {
        String msg =
                String.format("The EstadoSiniestro Id %s is different from the Url Id", estadoSiniestro.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, estadoSiniestro.getId(), msg);
        this.estadoSiniestroService.deleteEstadoSiniestro(estadoSiniestro);
    }
}
