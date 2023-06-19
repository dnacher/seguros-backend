package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.service.CompaniaService;
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
@RequestMapping("/api/v1/companias")
public class CompaniaController {

    private final CompaniaService companiaService;

    public CompaniaController(CompaniaService companiaService) {
        this.companiaService = companiaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCompania() {
        try{
            return ResponseEntity.ok().body(this.companiaService.getCompanias());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCompaniaByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.companiaService.getCompaniaByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCompaniaById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.companiaService.getCompaniaById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCompania(@RequestBody Compania compania) {
        try{
            return ResponseEntity.ok().body(this.companiaService.saveCompania(compania));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCompania(@PathVariable Integer id, @RequestBody Compania compania) {
        try{
            String msg = String.format("The Compania Id %s is different from the Url Id", compania.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, compania.getId(), msg);
            return ResponseEntity.ok().body(this.companiaService.updateCompania(compania));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCompania(@PathVariable Integer id, Compania compania) {
        try{
            String msg = String.format("The Compania Id %s is different from the Url Id", compania.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, compania.getId(), msg);
            this.companiaService.deleteCompania(compania);
            return ResponseEntity.ok().body("Compañia borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
