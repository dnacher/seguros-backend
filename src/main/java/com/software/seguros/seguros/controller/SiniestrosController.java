package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import com.software.seguros.seguros.service.SiniestroService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/siniestros")
public class SiniestrosController {

    private final SiniestroService siniestroService;

    public SiniestrosController(SiniestroService siniestroService) {
        this.siniestroService = siniestroService;
    }

    @GetMapping(value = "/")
    public List<Siniestro> getSiniestro() {
        return this.siniestroService.getSiniestros();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Siniestro getSiniestroByUuid(@PathVariable String uuid) {
        return this.siniestroService.getSiniestroByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Siniestro getSiniestroById(@PathVariable Integer id) {
        return this.siniestroService.getSiniestroById(id);
    }

    @PostMapping(value = "/poliza")
    public List<Siniestro> findByPoliza(@RequestBody Poliza poliza) {
        return this.siniestroService.findByPoliza(poliza);
    }

    @PostMapping(value = "/cliente")
    public List<Siniestro> findByCliente(@RequestBody Cliente cliente) {
        return this.siniestroService.findByCliente(cliente);
    }

    @PostMapping(value = "/")
    public Siniestro saveSiniestro(@RequestBody Siniestro siniestro) {
        return this.siniestroService.saveSiniestro(siniestro);
    }

    @PutMapping(value = "/{id}")
    public Siniestro updateSiniestro(
            @PathVariable Integer id, @RequestBody Siniestro siniestro) {
        String msg =
                String.format("The Siniestro Id %s is different from the Url Id", siniestro.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, siniestro.getId(), msg);
        return this.siniestroService.updateSiniestro(siniestro);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSiniestro(@PathVariable Integer id, Siniestro siniestro) {
        String msg =
                String.format("The Siniestro Id %s is different from the Url Id", siniestro.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, siniestro.getId(), msg);
        this.siniestroService.deleteSiniestro(siniestro);
    }
}
