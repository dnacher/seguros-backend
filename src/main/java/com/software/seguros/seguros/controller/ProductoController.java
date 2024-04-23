package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
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

import java.util.HashMap;
import java.util.Map;


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
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", productoService.getProductos());
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getProductoByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", productoService.getProductoByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", productoService.getProductoById(id));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "/tipo-producto")
    public ResponseEntity<?> findByTipoProducto(@RequestBody TipoProducto tipoProducto) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", productoService.findByTipoProducto(tipoProducto));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(producto.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PRODUCTO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = productoService.validarDatos(producto);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", productoService.saveProducto(producto));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(producto.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PRODUCTO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = productoService.validarDatos(producto);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(productoService.updateProducto(producto));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            productoService.deleteProducto(id);
            return ResponseEntity.ok().body("Poliza borrada ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
