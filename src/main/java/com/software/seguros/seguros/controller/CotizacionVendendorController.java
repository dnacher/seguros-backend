package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.service.CotizacionVendedorService;
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
@RequestMapping("/api/v1/cotizacion-vendedores")
public class CotizacionVendendorController {

    private final CotizacionVendedorService cotizacionVendedorService;

    public CotizacionVendendorController(CotizacionVendedorService cotizacionVendedorService) {
        this.cotizacionVendedorService = cotizacionVendedorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCotizacionVendedor() {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.getCotizacionVendedores());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCotizacionVendedorById(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.getCotizacionVendedorByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCotizacionVendedorById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.getCotizacionVendedorById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/vendedor")
    public ResponseEntity<?> findByVendedor(Vendedor vendedor) {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.findByVendedor(vendedor));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/producto")
    public ResponseEntity<?> findByProducto(Producto producto) {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.findByProducto(producto));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCotizacionVendedor(@RequestBody CotizacionVendedor cotizacionVendedor) {
        try{
            return ResponseEntity.ok().body(this.cotizacionVendedorService.saveCotizacionVendedor(cotizacionVendedor));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCotizacionVendedor(@PathVariable Integer id,
                                                      @RequestBody CotizacionVendedor cotizacionVendedor) {
        try{
            String msg = String.format("The CotizacionVendedor Id %s is different from the Url Id",
                                        cotizacionVendedor.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cotizacionVendedor.getId(), msg);
            return ResponseEntity.ok().body(this.cotizacionVendedorService.updateCotizacionVendedor(cotizacionVendedor));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCotizacionVendedor(@PathVariable Integer id,
                                                      @RequestBody CotizacionVendedor cotizacionVendedor) {
        try{
            String msg =
                    String.format("The CotizacionVendedor Id %s is different from the Url Id",
                                    cotizacionVendedor.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cotizacionVendedor.getId(), msg);
            this.cotizacionVendedorService.deleteCotizacionVendedor(cotizacionVendedor);
            return ResponseEntity.ok().body("Cotizacion borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
