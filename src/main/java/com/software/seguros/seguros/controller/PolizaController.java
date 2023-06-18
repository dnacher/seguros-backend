package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTO;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTOInt;
import com.software.seguros.seguros.service.PolizaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping(value = "")
    public List<Poliza> getPoliza() {
        return this.polizaService.getPolizas();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Poliza getPolizaByUuid(@PathVariable String uuid) {
        return this.polizaService.getPolizaByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Poliza getPolizaById(@PathVariable Integer id) {
        return this.polizaService.getPolizaById(id);
    }

    @GetMapping(value = "/premio/{desde}/{hasta}")
    public List<PolizaDTO> getTotalPremioByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                  @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getTotalPremioByFechasGroupByProductos(desde, hasta);
    }

    @GetMapping(value = "/prima/{desde}/{hasta}")
    public List<PolizaDTO> getTotalPrimaByFechasGroupByProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                                    @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getTotalPrimaByFechasGroupByProductos(desde, hasta);
    }

    @GetMapping(value = "/count/{desde}/{hasta}")
    public List<PolizaDTO> getCountProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                             @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getCountProductos(desde, hasta);
    }

    @GetMapping(value = "/{desde}/{hasta}")
    public List<Poliza> getPolizasByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date desde,
                                          @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getPolizasByFecha(desde, hasta);
    }

    @GetMapping(value = "/vencimiento/{desde}/{hasta}")
    public List<Poliza> getPolizasVencimientoByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                     @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getPolizasVencimientoByFecha(desde, hasta);
    }

    @GetMapping(value = "/sum-prima/{desde}/{hasta}")
    public List<PolizaDTOInt> getSUMPrimaProductos(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                   @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getSUMPrimaProductos(desde, hasta);
    }

    @GetMapping(value = "/comisiones/{desde}/{hasta}")
    public List<PolizaDTOInt> getPolizasComisionesByFecha(@PathVariable(name = "desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
                                                          @PathVariable(name = "hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
        return this.polizaService.getPolizasComisionesByFecha(desde, hasta);
    }

    @PostMapping(value = "/cliente")
    public List<Poliza> findByCliente(@RequestBody Cliente cliente) {
        return this.polizaService.findByCliente(cliente);
    }

    @PostMapping(value = "/cliente-nuevo-renovacion")
    public List<Poliza> findByClienteNuevoYRenovacion(@RequestBody Cliente cliente) {
        return this.polizaService.findByClienteNuevoYRenovacion(cliente);
    }

    @PostMapping(value = "/cliente-endoso")
    public List<Poliza> findByClienteAndEstadoEndoso(@RequestBody Cliente cliente) {
        return this.polizaService.findByClienteAndEstadoEndoso(cliente);
    }

    @PostMapping(value = "")
    public Poliza savePoliza(@RequestBody Poliza poliza) {
        return this.polizaService.savePoliza(poliza);
    }

    @PutMapping(value = "/{id}")
    public Poliza updatePoliza(
            @PathVariable Integer id, @RequestBody Poliza poliza) {
        String msg =
                String.format("The Poliza Id %s is different from the Url Id", poliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, poliza.getId(), msg);
        return this.polizaService.updatePoliza(poliza);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePoliza(@PathVariable Integer id, Poliza poliza) {
        String msg =
                String.format("The Poliza Id %s is different from the Url Id", poliza.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, poliza.getId(), msg);
        this.polizaService.deletePoliza(poliza);
    }
}
