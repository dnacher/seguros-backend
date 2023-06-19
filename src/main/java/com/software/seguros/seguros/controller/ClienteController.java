package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.service.ClienteService;
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
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCliente() {
        try{
            return ResponseEntity.ok().body(this.clienteService.getClientes());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getClienteByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.clienteService.getClienteByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.clienteService.getClienteById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }

    }

    @GetMapping(value = "/fecha-nacimiento/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<?> findAllByFechaNacimientoBetween(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
                                                             @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd")Date fechaHasta) {
        try{
            return ResponseEntity.ok().body(this.clienteService.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/aniversario/{diaInicio}/{diaFinal}/{mes}")
    public ResponseEntity<?> getAniversary(@PathVariable int diaInicio,@PathVariable int diaFinal, @PathVariable int mes) {
        try{
            return ResponseEntity.ok().body(this.clienteService.getAniversary(diaInicio, diaFinal, mes));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok().body(this.clienteService.saveCliente(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try{
            String msg = String.format("El id de cliente: %s es diferente al de la url", cliente.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cliente.getId(), msg);
            return ResponseEntity.ok().body(this.clienteService.updateCliente(cliente));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id, Cliente cliente) {
        try{
            String msg =
                    String.format("The Cliente Id %s is different from the Url Id", cliente.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cliente.getId(), msg);
            this.clienteService.deleteCliente(cliente);
            return ResponseEntity.ok().body("Cliente borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
