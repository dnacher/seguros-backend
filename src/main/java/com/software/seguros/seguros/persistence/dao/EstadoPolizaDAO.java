package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import com.software.seguros.seguros.persistence.repository.EstadoPolizaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EstadoPolizaDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final EstadoPolizaRepository repository;
    
    public EstadoPolizaDAO(EstadoPolizaRepository repository){
        this.repository = repository;
    }

    public List<EstadoPoliza> getEstadoPolizas() {
        List<EstadoPoliza> estadoPolizas = new ArrayList<>();
        this.repository.findAll().forEach(estadoPoliza -> estadoPolizas.add(estadoPoliza));
        log.info( "getEstadoPolizas");
        return estadoPolizas;
    }

    public EstadoPoliza getEstadoPolizaByUuid(String uuid) throws SegurosException {
        log.info( "getEstadoPolizaById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El EstadoPoliza uuid %s no existe", uuid);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public EstadoPoliza getEstadoPolizaById(Integer id) throws SegurosException {
        log.info( "getEstadoPolizaById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El EstadoPoliza id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public EstadoPoliza saveEstadoPoliza(EstadoPoliza estadoPoliza) throws SegurosException {
        log.info( "saveEstadoPoliza " + estadoPoliza.toStringLog());
        return this.repository.save(estadoPoliza);
    }

    public List<EstadoPoliza> saveEstadoPoliza(List<EstadoPoliza> policies) throws SegurosException {
        List<EstadoPoliza> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteEstadoPoliza(Integer id) {
        log.info( "deleteEstadoPoliza " + id);
        this.repository.deleteById(id);
    }

    public EstadoPoliza updateEstadoPoliza(EstadoPoliza estadoPoliza) throws SegurosException {
        if (estadoPoliza.getId() != null) {
            log.info( "updateEstadoPoliza " + estadoPoliza.toStringLog());
            if(estadoPoliza.getUuid()==null){
                estadoPoliza.setUuid(UUID.randomUUID().toString());
            }
            if(estadoPoliza.getCreated()==null){
                estadoPoliza.setCreated(LocalDateTime.now());
            }
            return this.repository.save(estadoPoliza);
        } else {
            String nombre = "El estado";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }
}
