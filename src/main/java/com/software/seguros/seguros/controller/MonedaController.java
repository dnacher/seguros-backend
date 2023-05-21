package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Moneda;
import com.software.seguros.seguros.service.MonedaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/monedas")
public class MonedaController {

    private final MonedaService monedaService;

    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping(value = "/")
    public List<Moneda> getMoneda() {
        return this.monedaService.getMonedas();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Moneda getMonedaByUuid(@PathVariable String uuid) {
        return this.monedaService.getMonedaByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Moneda getMonedaById(@PathVariable Integer id) {
        return this.monedaService.getMonedaById(id);
    }

    @PostMapping(value = "/")
    public Moneda saveMoneda(@RequestBody Moneda moneda) {
        return this.monedaService.saveMoneda(moneda);
    }

    @PutMapping(value = "/{id}")
    public Moneda updateMoneda(
            @PathVariable Integer id, @RequestBody Moneda moneda) {
        String msg =
                String.format("The Moneda Id %s is different from the Url Id", moneda.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, moneda.getId(), msg);
        return this.monedaService.updateMoneda(moneda);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMoneda(@PathVariable Integer id, Moneda moneda) {
        String msg =
                String.format("The Moneda Id %s is different from the Url Id", moneda.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, moneda.getId(), msg);
        this.monedaService.deleteMoneda(moneda);
    }
}
