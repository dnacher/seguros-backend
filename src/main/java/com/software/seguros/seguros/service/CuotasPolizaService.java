package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import com.software.seguros.seguros.persistence.repository.CuotaPolizaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CuotasPolizaService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CuotaPolizaRepository repository;
    
    public CuotasPolizaService(CuotaPolizaRepository repository){
        this.repository = repository;
    }

    public List<CuotasPoliza> getCuotasPolizas() {
        log.info("getCuotasPoliza");
        return this.repository.findAll();
    }

    public CuotasPoliza getCuotasPolizaByUuid(String uuid) throws SegurosException {
        log.info( "getCuotasPolizaById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cuota uuid %s no existe", uuid);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public CuotasPoliza getCuotasPolizaById(Integer id) throws SegurosException {
        log.info( "getCuotasPolizaById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cuota id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public CuotasPoliza saveCuotasPoliza(CuotasPoliza cuotasPoliza) throws SegurosException {
        log.info( "Guardar cuota poliza " + cuotasPoliza.toStringLog());
        return this.repository.save(cuotasPoliza);
    }

    public List<CuotasPoliza> saveCuotasPolizas(List<CuotasPoliza> policies) throws SegurosException {
        List<CuotasPoliza> finalList = new ArrayList<>();
        this.repository.saveAll(policies).forEach(finalList::add);
        return finalList;
    }

    public void deleteCuotasPoliza(Integer id) {
        log.info( "borrar CuotasPoliza " + id);
        this.repository.deleteById(id);
    }

    public CuotasPoliza updateCuotasPoliza(CuotasPoliza cuotasPoliza) throws SegurosException {
        if (cuotasPoliza.getId() != null) {
            log.info( "Actualizar CuotasPoliza " + cuotasPoliza.toStringLog());
            if(cuotasPoliza.getUuid()==null){
                cuotasPoliza.setUuid(UUID.randomUUID().toString());
            }
            if(cuotasPoliza.getCreated()==null){
                cuotasPoliza.setCreated(LocalDateTime.now());
            }
            return this.repository.save(cuotasPoliza);
        } else {
            String nombre = "La cuota";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Codigo validarDatos(CuotasPoliza cuotasPoliza) {
        if (cuotasPoliza.getPoliza()==null) {
            return Codigo.CUOTA_POLIZA_FALTA_POLIZA;
        }
        return Codigo.OK;
    }

}
