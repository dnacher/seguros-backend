package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.service.CompaniaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/companias")
public class CompaniaController {

    private final CompaniaService companiaService;

    public CompaniaController(CompaniaService companiaService) {
        this.companiaService = companiaService;
    }

    @GetMapping(value = "")
    public List<Compania> getCompania() {
        return this.companiaService.getCompanias();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Compania getCompaniaByUuid(@PathVariable String uuid) {
        return this.companiaService.getCompaniaByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Compania getCompaniaById(@PathVariable Integer id) {
        return this.companiaService.getCompaniaById(id);
    }

    @PostMapping(value = "")
    public Compania saveCompania(@RequestBody Compania compania) {
        return this.companiaService.saveCompania(compania);
    }

    @PutMapping(value = "/{id}")
    public Compania updateCompania(
            @PathVariable Integer id, @RequestBody Compania compania) {
        String msg =
                String.format("The Compania Id %s is different from the Url Id", compania.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, compania.getId(), msg);
        return this.companiaService.updateCompania(compania);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCompania(@PathVariable Integer id, Compania compania) {
        String msg =
                String.format("The Compania Id %s is different from the Url Id", compania.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, compania.getId(), msg);
        this.companiaService.deleteCompania(compania);
    }
}
