package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import com.software.seguros.seguros.persistence.repository.EstadoSiniestroRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EstadoSiniestroDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final EstadoSiniestroRepository repository;
    
    public EstadoSiniestroDAO(EstadoSiniestroRepository repository){
        this.repository = repository;
    }

    public List<EstadoSiniestro> getEstadoSiniestros() {
        List<EstadoSiniestro> estadoSiniestros = new ArrayList<>();
        this.repository.findAll().forEach(estadoSiniestro -> estadoSiniestros.add(estadoSiniestro));
        log.info(  "getEstadoSiniestros");
        return estadoSiniestros;
    }

    public EstadoSiniestro getEstadoSiniestroByUuid(String uuid) throws SegurosException {
        log.info( "getEstadoSiniestro " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The EstadoSiniestro id %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public EstadoSiniestro getEstadoSiniestroById(Integer id) throws SegurosException {
        log.info( "getEstadoSiniestroById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The EstadoSiniestro id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public EstadoSiniestro saveEstadoSiniestro(EstadoSiniestro estadoSiniestro) throws SegurosException {
        log.info( "saveEstadoSiniestro " + estadoSiniestro.toStringLog());
        return this.repository.save(estadoSiniestro);
    }

    public List<EstadoSiniestro> saveEstadoSiniestro(List<EstadoSiniestro> policies) throws SegurosException {
        List<EstadoSiniestro> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteEstadoSiniestro(EstadoSiniestro estadoSiniestro) {
        log.info( "deleteEstadoSiniestro " + estadoSiniestro.toStringLog());
        this.repository.delete(estadoSiniestro);
    }

    public EstadoSiniestro updateEstadoSiniestro(EstadoSiniestro estadoSiniestro) throws SegurosException {
        if (estadoSiniestro.getId() != null) {
            log.info( "updateEstadoSiniestro " + estadoSiniestro.toStringLog());
            if(estadoSiniestro.getUuid()==null){
                estadoSiniestro.setUuid(UUID.randomUUID().toString());
            }
            if(estadoSiniestro.getCreated()==null){
                estadoSiniestro.setCreated(LocalDateTime.now());
            }
            return this.repository.save(estadoSiniestro);
        } else {
            String msg = String.format("Cannot update a EstadoSiniestro without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }
}
