package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Ingreso;
import com.software.seguros.seguros.service.IngresoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/ingresos")
public class IngresoController {

    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @GetMapping(value = "/")
    public List<Ingreso> getIngreso() {
        return this.ingresoService.getIngresos();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Ingreso getIngresoById(@PathVariable String uuid) {
        return this.ingresoService.getIngresoByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Ingreso getIngresoById(@PathVariable Integer id) {
        return this.ingresoService.getIngresoById(id);
    }

    @PostMapping(value = "/")
    public Ingreso saveIngreso(@RequestBody Ingreso ingreso) {
        return this.ingresoService.saveIngreso(ingreso);
    }

    @PutMapping(value = "/{id}")
    public Ingreso updateIngreso(
            @PathVariable Integer id, @RequestBody Ingreso ingreso) {
        String msg =
                String.format("The Ingreso Id %s is different from the Url Id", ingreso.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, ingreso.getId(), msg);
        return this.ingresoService.updateIngreso(ingreso);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteIngreso(@PathVariable Integer id, Ingreso ingreso) {
        String msg =
                String.format("The Ingreso Id %s is different from the Url Id", ingreso.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, ingreso.getId(), msg);
        this.ingresoService.deleteIngreso(ingreso);
    }
}
