package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.service.FormaPagoService;
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
@RequestMapping("/api/v1/forma-pagos")
public class FormaPagoController {

    private final FormaPagoService formaPagoService;

    public FormaPagoController(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getFormaPago() {
        try{
            return ResponseEntity.ok().body(this.formaPagoService.getFormaPagos());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getFormaPagoByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.formaPagoService.getFormaPagoByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getFormaPagoById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.formaPagoService.getFormaPagoById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveFormaPago(@RequestBody FormaPago formaPago) {
        try{
            return ResponseEntity.ok().body(this.formaPagoService.saveFormaPago(formaPago));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateFormaPago(
            @PathVariable Integer id, @RequestBody FormaPago formaPago) {
        try{
            String msg = String.format("The FormaPago Id %s is different from the Url Id", formaPago.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, formaPago.getId(), msg);
            return ResponseEntity.ok().body(this.formaPagoService.updateFormaPago(formaPago));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFormaPago(@PathVariable Integer id, FormaPago formaPago) {
        try{
            String msg =
                    String.format("The FormaPago Id %s is different from the Url Id", formaPago.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, formaPago.getId(), msg);
            this.formaPagoService.deleteFormaPago(formaPago);
            return ResponseEntity.ok().body("Forma de pago borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
