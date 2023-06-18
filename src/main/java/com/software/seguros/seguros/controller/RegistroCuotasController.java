package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import com.software.seguros.seguros.service.RegistroCuotasService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/registro-cuotas")
public class RegistroCuotasController {

    private final RegistroCuotasService registroCuotasService;

    public RegistroCuotasController(RegistroCuotasService registroCuotasService) {
        this.registroCuotasService = registroCuotasService;
    }

    @GetMapping(value = "")
    public List<RegistroCuotas> getRegistroCuotas() {
        return this.registroCuotasService.getRegistroCuotas();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public RegistroCuotas getRegistroCuotasByUuid(@PathVariable String uuid) {
        return this.registroCuotasService.getRegistroCuotasByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public RegistroCuotas getRegistroCuotasById(@PathVariable Integer id) {
        return this.registroCuotasService.getRegistroCuotasById(id);
    }

    @GetMapping(value = "/cuotas")
    public List<RegistroCuotas> getRegistrosCuotasConCuotas() {
        return this.registroCuotasService.getRegistrosCuotasConCuotas();
    }

    @PostMapping(value = "")
    public RegistroCuotas saveRegistroCuotas(@RequestBody RegistroCuotas registroCuotas) {
        return this.registroCuotasService.saveRegistroCuotas(registroCuotas);
    }

    @PutMapping(value = "/{id}")
    public RegistroCuotas updateRegistroCuotas(
            @PathVariable Integer id, @RequestBody RegistroCuotas registroCuotas) {
        String msg =
                String.format("The RegistroCuotas Id %s is different from the Url Id", registroCuotas.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, registroCuotas.getId(), msg);
        return this.registroCuotasService.updateRegistroCuotas(registroCuotas);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRegistroCuotas(@PathVariable Integer id, RegistroCuotas registroCuotas) {
        String msg =
                String.format("The RegistroCuotas Id %s is different from the Url Id", registroCuotas.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, registroCuotas.getId(), msg);
        this.registroCuotasService.deleteRegistroCuotas(registroCuotas);
    }
}
