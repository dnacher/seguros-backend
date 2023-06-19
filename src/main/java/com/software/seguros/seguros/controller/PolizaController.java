package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.service.PolizaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPoliza() {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizas());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getPolizaByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizaByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPolizaById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizaById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/premio/{desde}/{hasta}")
    public ResponseEntity<?> getTotalPremioByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                    @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getTotalPremioByFechasGroupByProductos(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/prima/{desde}/{hasta}")
    public ResponseEntity<?> getTotalPrimaByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                   @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getTotalPrimaByFechasGroupByProductos(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/count/{desde}/{hasta}")
    public ResponseEntity<?> getCountProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                               @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getCountProductos(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date desde,
                                               @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizasByFecha(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/vencimiento/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasVencimientoByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                     @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizasVencimientoByFecha(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/sum-prima/{desde}/{hasta}")
    public ResponseEntity<?> getSUMPrimaProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                  @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getSUMPrimaProductos(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/comisiones/{desde}/{hasta}")
    public ResponseEntity<?> getPolizasComisionesByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                         @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        try{
            return ResponseEntity.ok().body(this.polizaService.getPolizasComisionesByFecha(desde, hasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<?> findByCliente(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok().body(this.polizaService.findByCliente(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cliente-nuevo-renovacion")
    public ResponseEntity<?> findByClienteNuevoYRenovacion(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok().body(this.polizaService.findByClienteNuevoYRenovacion(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cliente-endoso")
    public ResponseEntity<?> findByClienteAndEstadoEndoso(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok().body(this.polizaService.findByClienteAndEstadoEndoso(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> savePoliza(@RequestBody Poliza poliza) {
        try{
            return ResponseEntity.ok().body(this.polizaService.savePoliza(poliza));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePoliza(
            @PathVariable Integer id, @RequestBody Poliza poliza) {
        try{
            String msg = String.format("The Poliza Id %s is different from the Url Id", poliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, poliza.getId(), msg);
            return ResponseEntity.ok().body(this.polizaService.updatePoliza(poliza));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePoliza(@PathVariable Integer id, Poliza poliza) {
        try{
            String msg = String.format("The Poliza Id %s is different from the Url Id", poliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, poliza.getId(), msg);
            this.polizaService.deletePoliza(poliza);
            return ResponseEntity.ok().body("Poliza borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
