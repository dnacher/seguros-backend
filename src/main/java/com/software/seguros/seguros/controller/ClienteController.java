package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.service.ClienteService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.http.HttpStatus;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final ResponseFactory responseFactory;

    public ClienteController(ClienteService clienteService, ResponseFactory responseFactory) {
        this.clienteService = clienteService;
        this.responseFactory = responseFactory;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCliente() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", clienteService.getClientes());
            return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getClienteByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", clienteService.getClienteByUuid(uuid));
            return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", clienteService.getClienteById(id));
            return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/fecha-nacimiento/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<?> findAllByFechaNacimientoBetween(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
                                                             @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd")Date fechaHasta) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", clienteService.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta));
            return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/aniversario/{diaInicio}/{diaFinal}/{mes}")
    public ResponseEntity<?> getAniversary(@PathVariable int diaInicio,@PathVariable int diaFinal, @PathVariable int mes) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", clienteService.getAniversary(diaInicio, diaFinal, mes));
            return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cliente.getId()!=null) {
                return responseFactory.handleErrorCodes(body, Codigo.CLIENTE_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = clienteService.validarDatos(cliente);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.clienteService.saveCliente(cliente));
                return responseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return responseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cliente.getId()==null) {
                return responseFactory.handleErrorCodes(body, Codigo.CLIENTE_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = clienteService.validarDatos(cliente);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.clienteService.updateCliente(cliente));
            } else {
                return responseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().body("Cliente borrado ID:" + id);
        } catch (SegurosException ex){
            body.put("error", ex.getMessage());
            return responseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
