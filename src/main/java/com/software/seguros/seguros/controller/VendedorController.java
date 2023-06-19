package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.service.VendedorService;
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
@RequestMapping("/api/v1/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getVendedor() {
        try{
            return ResponseEntity.ok().body(this.vendedorService.getVendedores());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getVendedorByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.vendedorService.getVendedorByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getVendedorById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.vendedorService.getVendedorById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveVendedor(@RequestBody Vendedor vendedor) {
        try{
            return ResponseEntity.ok().body(this.vendedorService.saveVendedor(vendedor));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateVendedor(
            @PathVariable Integer id, @RequestBody Vendedor vendedor) {
        try{
            String msg = String.format("The Vendedor Id %s is different from the Url Id", vendedor.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, vendedor.getId(), msg);
            return ResponseEntity.ok().body(this.vendedorService.updateVendedor(vendedor));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVendedor(@PathVariable Integer id, Vendedor vendedor) {
        try{
            String msg = String.format("The Vendedor Id %s is different from the Url Id", vendedor.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, vendedor.getId(), msg);
            this.vendedorService.deleteVendedor(vendedor);
            return ResponseEntity.ok().body("Vendedor borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
