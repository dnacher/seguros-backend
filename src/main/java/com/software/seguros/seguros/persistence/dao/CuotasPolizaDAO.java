package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import com.software.seguros.seguros.persistence.repository.CuotaPolizaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CuotasPolizaDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CuotaPolizaRepository repository;
    
    public CuotasPolizaDAO(CuotaPolizaRepository repository){
        this.repository = repository;
    }

    public List<CuotasPoliza> getCuotasPolizas() {
        log.info("getCuotasPoliza");
        List<CuotasPoliza> lista = this.repository.findAll();
        return lista;
    }

    public CuotasPoliza getCuotasPolizaByUuid(String uuid) throws SegurosException {
        log.info( "getCuotasPolizaById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cuota con este id no existe", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public CuotasPoliza getCuotasPolizaById(Integer id) throws SegurosException {
        log.info( "getCuotasPolizaById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cuota con este id no existe", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public CuotasPoliza saveCuotasPoliza(CuotasPoliza cuotasPoliza) throws SegurosException {
        log.info( "Guardar cuota poliza " + cuotasPoliza.toStringLog());
        return this.repository.save(cuotasPoliza);
    }

    public List<CuotasPoliza> saveCuotasPolizas(List<CuotasPoliza> policies) throws SegurosException {
        List<CuotasPoliza> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
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
            String msg = String.format("No se puede actualizar CuotasPoliza sin Id asociado");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
    
}
