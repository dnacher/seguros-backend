package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.service.TipoProductoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/tipo-productos")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    public TipoProductoController(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @GetMapping(value = "/")
    public List<TipoProducto> getTipoProducto() {
        return this.tipoProductoService.getTipoProductos();
    }

    @GetMapping(value = "/{id}")
    public TipoProducto getTipoProductoById(@PathVariable Integer id) {
        return this.tipoProductoService.getTipoProductoById(id);
    }

    @PostMapping(value = "/")
    public TipoProducto saveTipoProducto(@RequestBody TipoProducto tipoProducto) {
        return this.tipoProductoService.saveTipoProducto(tipoProducto);
    }

    @PutMapping(value = "/{id}")
    public TipoProducto updateTipoProducto(
            @PathVariable Integer id, @RequestBody TipoProducto tipoProducto) {
        String msg =
                String.format("The TipoProducto Id %s is different from the Url Id", tipoProducto.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoProducto.getId(), msg);
        return this.tipoProductoService.updateTipoProducto(tipoProducto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTipoProducto(@PathVariable Integer id, TipoProducto tipoProducto) {
        String msg =
                String.format("The TipoProducto Id %s is different from the Url Id", tipoProducto.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoProducto.getId(), msg);
        this.tipoProductoService.deleteTipoProducto(tipoProducto);
    }
}
