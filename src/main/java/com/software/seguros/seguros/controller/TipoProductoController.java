package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
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

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", tipoProductoService.getTipoProductos());
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getTipoProductoByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", tipoProductoService.getTipoProductoByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTipoProductoById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", tipoProductoService.getTipoProductoById(id));
            return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTipoProducto(@RequestBody TipoProducto tipoProducto) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(tipoProducto.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.TIPO_PRODUCTO_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = tipoProductoService.validarDatos(tipoProducto);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", tipoProductoService.saveTipoProducto(tipoProducto));
                return ResponseFactory.createResponseEntity(body, "", org.springframework.http.HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateTipoProducto(@RequestBody TipoProducto tipoProducto) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(tipoProducto.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.TIPO_PRODUCTO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = tipoProductoService.validarDatos(tipoProducto);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(tipoProductoService.updateTipoProducto(tipoProducto));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTipoProducto(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            tipoProductoService.deleteTipoProducto(id);
            return ResponseEntity.ok().body("Tipo producto borrado ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
