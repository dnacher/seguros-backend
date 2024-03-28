package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.service.TipoProductoService;
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
@RequestMapping("/api/v1/tipo-productos")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    public TipoProductoController(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getTipoProducto() {
        try{
            return ResponseEntity.ok().body(this.tipoProductoService.getTipoProductos());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getTipoProductoByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.tipoProductoService.getTipoProductoByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTipoProductoById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.tipoProductoService.getTipoProductoById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTipoProducto(@RequestBody TipoProducto tipoProducto) {
        try{
            return ResponseEntity.ok().body(this.tipoProductoService.saveTipoProducto(tipoProducto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTipoProducto(
            @PathVariable Integer id, @RequestBody TipoProducto tipoProducto) {
        try{
            String msg = String.format("The TipoProducto Id %s is different from the Url Id", tipoProducto.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoProducto.getId(), msg);
            return ResponseEntity.ok().body(this.tipoProductoService.updateTipoProducto(tipoProducto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTipoProducto(@PathVariable Integer id, TipoProducto tipoProducto) {
        try{
            String msg = String.format("The TipoProducto Id %s is different from the Url Id", tipoProducto.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoProducto.getId(), msg);
            this.tipoProductoService.deleteTipoProducto(tipoProducto);
            return ResponseEntity.ok().body("Tipo producto borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
