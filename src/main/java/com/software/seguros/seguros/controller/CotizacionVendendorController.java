package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.service.CotizacionVendedorService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/cotizacion-vendedores")
public class CotizacionVendendorController {

    private final CotizacionVendedorService cotizacionVendedorService;

    public CotizacionVendendorController(CotizacionVendedorService cotizacionVendedorService) {
        this.cotizacionVendedorService = cotizacionVendedorService;
    }

    @GetMapping(value = "/")
    public List<CotizacionVendedor> getCotizacionVendedor() {
        return this.cotizacionVendedorService.getCotizacionVendedores();
    }

    @GetMapping(value = "/{id}")
    public CotizacionVendedor getCotizacionVendedorById(@PathVariable Integer id) {
        return this.cotizacionVendedorService.getCotizacionVendedorById(id);
    }

    @PostMapping(value = "/vendedor")
    public List<CotizacionVendedor> findByVendedor(Vendedor vendedor) {
        return this.cotizacionVendedorService.findByVendedor(vendedor);
    }

    @PostMapping(value = "/producto")
    public List<CotizacionVendedor> findByProducto(Producto producto) {
        return this.cotizacionVendedorService.findByProducto(producto);
    }

    @PostMapping(value = "/")
    public CotizacionVendedor saveCotizacionVendedor(@RequestBody CotizacionVendedor cotizacionVendedor) {
        return this.cotizacionVendedorService.saveCotizacionVendedor(cotizacionVendedor);
    }

    @PutMapping(value = "/{id}")
    public CotizacionVendedor updateCotizacionVendedor(
            @PathVariable Integer id, @RequestBody CotizacionVendedor cotizacionVendedor) {
        String msg =
                String.format("The CotizacionVendedor Id %s is different from the Url Id", cotizacionVendedor.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cotizacionVendedor.getId(), msg);
        return this.cotizacionVendedorService.updateCotizacionVendedor(cotizacionVendedor);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCotizacionVendedor(@PathVariable Integer id, CotizacionVendedor cotizacionVendedor) {
        String msg =
                String.format("The CotizacionVendedor Id %s is different from the Url Id", cotizacionVendedor.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cotizacionVendedor.getId(), msg);
        this.cotizacionVendedorService.deleteCotizacionVendedor(cotizacionVendedor);
    }
}
