package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.service.ProductoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(value = "")
    public List<Producto> getProducto() {
        return this.productoService.getProductos();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Producto getProductoByUuid(@PathVariable String uuid) {
        return this.productoService.getProductoByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Producto getProductoById(@PathVariable Integer id) {
        return this.productoService.getProductoById(id);
    }

    @PostMapping(value = "/compania")
    public List<Producto> findByCompania(@RequestBody Compania compania) {
        return this.productoService.findByCompania(compania);
    }

    @PostMapping(value = "/tipo-producto")
    public List<Producto> findByTipoProducto(@RequestBody TipoProducto tipoProducto) {
        return this.productoService.findByTipoProducto(tipoProducto);
    }

    @PostMapping(value = "")
    public Producto saveProducto(@RequestBody Producto producto) {
        return this.productoService.saveProducto(producto);
    }

    @PutMapping(value = "/{id}")
    public Producto updateProducto(
            @PathVariable Integer id, @RequestBody Producto producto) {
        String msg =
                String.format("The Producto Id %s is different from the Url Id", producto.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, producto.getId(), msg);
        return this.productoService.updateProducto(producto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProducto(@PathVariable Integer id, Producto producto) {
        String msg =
                String.format("The Producto Id %s is different from the Url Id", producto.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, producto.getId(), msg);
        this.productoService.deleteProducto(producto);
    }
}
