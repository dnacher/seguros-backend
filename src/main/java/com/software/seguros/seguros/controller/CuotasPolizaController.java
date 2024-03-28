package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import com.software.seguros.seguros.service.CuotasPolizaService;
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
@RequestMapping("/api/v1/cuotas-polizas")
public class CuotasPolizaController {

    private final CuotasPolizaService cuotasPolizaService;

    public CuotasPolizaController(CuotasPolizaService cuotasPolizaService) {
        this.cuotasPolizaService = cuotasPolizaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getCuotasPoliza() {
        try{
            return ResponseEntity.ok().body(this.cuotasPolizaService.getCuotasPolizas());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> getCuotasPolizaByUuid(@PathVariable String uuid) {
        try{
            return ResponseEntity.ok().body(this.cuotasPolizaService.getCuotasPolizaByUuid(uuid));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCuotasPolizaById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(this.cuotasPolizaService.getCuotasPolizaById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCuotasPoliza(@RequestBody CuotasPoliza cuotasPoliza) {
        try{
            return ResponseEntity.ok().body(this.cuotasPolizaService.saveCuotasPoliza(cuotasPoliza));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCuotasPoliza(@PathVariable Integer id, @RequestBody CuotasPoliza cuotasPoliza) {
        try{
            String msg =  String.format("The CuotasPoliza Id %s is different from the Url Id", cuotasPoliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cuotasPoliza.getId(), msg);
            return ResponseEntity.ok().body(this.cuotasPolizaService.updateCuotasPoliza(cuotasPoliza));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCuotasPoliza(@PathVariable Integer id,
                                                @RequestBody CuotasPoliza cuotasPoliza) {
        try{
            String msg =
                    String.format("The CuotasPoliza Id %s is different from the Url Id", cuotasPoliza.getId());
            UtilsGeneral.validateUrlIdEqualsBodyId(id, cuotasPoliza.getId(), msg);
            this.cuotasPolizaService.deleteCuotasPoliza(cuotasPoliza);
            return ResponseEntity.ok().body("Cuota borrada ID:" + id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(ex.getMessage());
        }
    }
}
