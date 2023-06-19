package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.service.ProductoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getProducto() {
        try{
            return ResponseEntity.ok().body(this.productoService.getProductos());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getProductoByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.productoService.getProductoByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.productoService.getProductoById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/compania")
    public ResponseEntity<?> findByCompania(@RequestBody Compania compania) {
        try{
            return ResponseEntity.ok().body(this.productoService.findByCompania(compania));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/tipo-producto")
    public ResponseEntity<?> findByTipoProducto(@RequestBody TipoProducto tipoProducto) {
        try{
            return ResponseEntity.ok().body(this.productoService.findByTipoProducto(tipoProducto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto) {
        try{
            return ResponseEntity.ok().body(this.productoService.saveProducto(producto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProducto(
            @PathVariable Integer id, @RequestBody Producto producto) {
        try{
            String msg = String.format("The Producto Id %s is different from the Url Id", producto.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, producto.getId(), msg);
            return ResponseEntity.ok().body(this.productoService.updateProducto(producto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id, Producto producto) {
        try{
            String msg = String.format("The Producto Id %s is different from the Url Id", producto.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, producto.getId(), msg);
            this.productoService.deleteProducto(producto);
            return ResponseEntity.ok().body("Producto borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
