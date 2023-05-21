package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.service.VendedorService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/vendedors")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping(value = "/")
    public List<Vendedor> getVendedor() {
        return this.vendedorService.getVendedores();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Vendedor getVendedorByUuid(@PathVariable String uuid) {
        return this.vendedorService.getVendedorByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Vendedor getVendedorById(@PathVariable Integer id) {
        return this.vendedorService.getVendedorById(id);
    }

    @PostMapping(value = "/")
    public Vendedor saveVendedor(@RequestBody Vendedor vendedor) {
        return this.vendedorService.saveVendedor(vendedor);
    }

    @PutMapping(value = "/{id}")
    public Vendedor updateVendedor(
            @PathVariable Integer id, @RequestBody Vendedor vendedor) {
        String msg =
                String.format("The Vendedor Id %s is different from the Url Id", vendedor.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, vendedor.getId(), msg);
        return this.vendedorService.updateVendedor(vendedor);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteVendedor(@PathVariable Integer id, Vendedor vendedor) {
        String msg =
                String.format("The Vendedor Id %s is different from the Url Id", vendedor.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, vendedor.getId(), msg);
        this.vendedorService.deleteVendedor(vendedor);
    }
}
