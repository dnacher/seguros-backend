package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import com.software.seguros.seguros.persistence.model.DTO.BancoDTO;
import com.software.seguros.seguros.service.CuotasPolizaService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/cuotas-polizas")
public class CuotasPolizaController {

    private final CuotasPolizaService cuotasPolizaService;

    public CuotasPolizaController(CuotasPolizaService cuotasPolizaService) {
        this.cuotasPolizaService = cuotasPolizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCuotasPoliza() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cuotasPolizaService.getCuotasPolizas());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCuotasPolizaByUuid(@PathVariable String uuid) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cuotasPolizaService.getCuotasPolizaByUuid(uuid));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCuotasPolizaById(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", cuotasPolizaService.getCuotasPolizaById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCuotasPoliza(@RequestBody CuotasPoliza cuotasPoliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cuotasPoliza.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.CUOTA_POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = cuotasPolizaService.validarDatos(cuotasPoliza);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", cuotasPolizaService.saveCuotasPoliza(cuotasPoliza));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateCuotasPoliza(@RequestBody CuotasPoliza cuotasPoliza) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(cuotasPoliza.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.CUOTA_POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = cuotasPolizaService.validarDatos(cuotasPoliza);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(cuotasPolizaService.updateCuotasPoliza(cuotasPoliza));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (SegurosException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCuotasPoliza(@PathVariable Integer id) {
        Map<String, Object> body = new HashMap<>();
        try{
            cuotasPolizaService.deleteCuotasPoliza(id);
            return ResponseEntity.ok().body("Cuota poliza borrada ID: " + id);
        } catch (SegurosException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
