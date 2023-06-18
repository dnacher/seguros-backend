package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import com.software.seguros.seguros.service.CuotasPolizaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/cuotas-polizas")
public class CuotasPolizaController {

    private final CuotasPolizaService cuotasPolizaService;

    public CuotasPolizaController(CuotasPolizaService cuotasPolizaService) {
        this.cuotasPolizaService = cuotasPolizaService;
    }

    @GetMapping(value = "")
    public List<CuotasPoliza> getCuotasPoliza() {
        return this.cuotasPolizaService.getCuotasPolizas();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public CuotasPoliza getCuotasPolizaByUuid(@PathVariable String uuid) {
        return this.cuotasPolizaService.getCuotasPolizaByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public CuotasPoliza getCuotasPolizaById(@PathVariable Integer id) {
        return this.cuotasPolizaService.getCuotasPolizaById(id);
    }

    @PostMapping(value = "")
    public CuotasPoliza saveCuotasPoliza(@RequestBody CuotasPoliza cuotasPoliza) {
        return this.cuotasPolizaService.saveCuotasPoliza(cuotasPoliza);
    }

    @PutMapping(value = "/{id}")
    public CuotasPoliza updateCuotasPoliza(
            @PathVariable Integer id, @RequestBody CuotasPoliza cuotasPoliza) {
        String msg =
                String.format("The CuotasPoliza Id %s is different from the Url Id", cuotasPoliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cuotasPoliza.getId(), msg);
        return this.cuotasPolizaService.updateCuotasPoliza(cuotasPoliza);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCuotasPoliza(@PathVariable Integer id, CuotasPoliza cuotasPoliza) {
        String msg =
                String.format("The CuotasPoliza Id %s is different from the Url Id", cuotasPoliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cuotasPoliza.getId(), msg);
        this.cuotasPolizaService.deleteCuotasPoliza(cuotasPoliza);
    }
}
