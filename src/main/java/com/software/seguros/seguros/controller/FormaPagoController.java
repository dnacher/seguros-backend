package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.service.FormaPagoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/forma-pagos")
public class FormaPagoController {

    private final FormaPagoService formaPagoService;

    public FormaPagoController(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

    @GetMapping(value = "/")
    public List<FormaPago> getFormaPago() {
        return this.formaPagoService.getFormaPagos();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public FormaPago getFormaPagoByUuid(@PathVariable String uuid) {
        return this.formaPagoService.getFormaPagoByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public FormaPago getFormaPagoById(@PathVariable Integer id) {
        return this.formaPagoService.getFormaPagoById(id);
    }

    @PostMapping(value = "/")
    public FormaPago saveFormaPago(@RequestBody FormaPago formaPago) {
        return this.formaPagoService.saveFormaPago(formaPago);
    }

    @PutMapping(value = "/{id}")
    public FormaPago updateFormaPago(
            @PathVariable Integer id, @RequestBody FormaPago formaPago) {
        String msg =
                String.format("The FormaPago Id %s is different from the Url Id", formaPago.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, formaPago.getId(), msg);
        return this.formaPagoService.updateFormaPago(formaPago);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteFormaPago(@PathVariable Integer id, FormaPago formaPago) {
        String msg =
                String.format("The FormaPago Id %s is different from the Url Id", formaPago.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, formaPago.getId(), msg);
        this.formaPagoService.deleteFormaPago(formaPago);
    }
}
