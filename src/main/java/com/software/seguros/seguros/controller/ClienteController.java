package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.service.ClienteService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/")
    public List<Cliente> getCliente() {
        return this.clienteService.getClientes();
    }

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return this.clienteService.getClienteById(id);
    }

    @GetMapping(value = "/fecha-nacimiento/{fechaDesde}/{fechaHasta}")
    public List<Cliente> findAllByFechaNacimientoBetween(@PathVariable Date fechaDesde,@PathVariable Date fechaHasta) {
        return this.clienteService.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta);
    }

    @GetMapping(value = "/aniversario/{diaInicio}/{diaFinal}/{mes}")
    public List<Cliente> getAniversary(@PathVariable int diaInicio,@PathVariable int diaFinal, @PathVariable int mes) {
        return this.clienteService.getAniversary(diaInicio, diaFinal, mes);
    }

    @PostMapping(value = "/")
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return this.clienteService.saveCliente(cliente);
    }

    @PutMapping(value = "/{id}")
    public Cliente updateCliente(
            @PathVariable Integer id, @RequestBody Cliente cliente) {
        String msg =
                String.format("The Cliente Id %s is different from the Url Id", cliente.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cliente.getId(), msg);
        return this.clienteService.updateCliente(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCliente(@PathVariable Integer id, Cliente cliente) {
        String msg =
                String.format("The Cliente Id %s is different from the Url Id", cliente.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, cliente.getId(), msg);
        this.clienteService.deleteCliente(cliente);
    }
}
