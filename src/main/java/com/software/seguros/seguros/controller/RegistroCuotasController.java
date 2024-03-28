package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import com.software.seguros.seguros.service.RegistroCuotasService;
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
@RequestMapping("/api/v1/registro-cuotas")
public class RegistroCuotasController {

    private final RegistroCuotasService registroCuotasService;

    public RegistroCuotasController(RegistroCuotasService registroCuotasService) {
        this.registroCuotasService = registroCuotasService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getRegistroCuotas() {
        try{
            return ResponseEntity.ok().body(this.registroCuotasService.getRegistroCuotas());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getRegistroCuotasByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.registroCuotasService.getRegistroCuotasByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRegistroCuotasById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.registroCuotasService.getRegistroCuotasById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/cuotas")
    public ResponseEntity<?> getRegistrosCuotasConCuotas() {
        try{
            return ResponseEntity.ok().body(this.registroCuotasService.getRegistrosCuotasConCuotas());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveRegistroCuotas(@RequestBody RegistroCuotas registroCuotas) {
        try{
            return ResponseEntity.ok().body(this.registroCuotasService.saveRegistroCuotas(registroCuotas));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateRegistroCuotas(
            @PathVariable Integer id, @RequestBody RegistroCuotas registroCuotas) {
        try{
            String msg = String.format("The RegistroCuotas Id %s is different from the Url Id", registroCuotas.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, registroCuotas.getId(), msg);
            return ResponseEntity.ok().body(this.registroCuotasService.updateRegistroCuotas(registroCuotas));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRegistroCuotas(@PathVariable Integer id, RegistroCuotas registroCuotas) {
        try{
            String msg = String.format("The RegistroCuotas Id %s is different from the Url Id", registroCuotas.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, registroCuotas.getId(), msg);
            this.registroCuotasService.deleteRegistroCuotas(registroCuotas);
            return ResponseEntity.ok().body("Registro borrado ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
