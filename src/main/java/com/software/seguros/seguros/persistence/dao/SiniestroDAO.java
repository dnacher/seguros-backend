package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Siniestro;
import com.software.seguros.seguros.persistence.repository.SiniestroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SiniestroDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final SiniestroRepository repository;
    
    public SiniestroDAO(SiniestroRepository repository){
        this.repository = repository;
    }

    public List<Siniestro> getSiniestros() {
        List<Siniestro> siniestros = new ArrayList<>();
        this.repository.findAll().forEach(siniestro -> siniestros.add(siniestro));
        log.info( "getSiniestros");
        return siniestros;
    }

    public Siniestro getSiniestroByUuid(String uuid) throws SegurosException {
        log.info( "getSiniestro " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El siniestro uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Siniestro getSiniestroById(Integer id) throws SegurosException {
        log.info( "getSiniestroById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El siniestro id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public List<Siniestro> findByPoliza(Integer polizaId){
        log.info( "findByPoliza: " + polizaId);
        return this.repository.findByPoliza_Id(polizaId);
    }

    public List<Siniestro> findByCliente(Integer clienteId){
        log.info( "findByCliente: " + clienteId);
        return this.repository.findByCliente_Id(clienteId);
    }

    public Integer countByNumeroSiniestro(String numeroSiniestro) {
        return repository.countByNumeroSiniestro(numeroSiniestro);
    }

    public Siniestro saveSiniestro(Siniestro siniestro) throws SegurosException {
        log.info("saveSiniestro " + siniestro.toStringLog());
        return this.repository.save(siniestro);
    }

    public List<Siniestro> saveSiniestros(List<Siniestro> siniestros) throws SegurosException {
        List<Siniestro> finalList = new ArrayList<>();
        this.repository
                .saveAll(siniestros)
                .forEach(
                        siniestro -> {
                            finalList.add(siniestro);
                        });
        return finalList;
    }

    public Siniestro updateSiniestros(Siniestro siniestro) throws SegurosException {
        if (siniestro.getId() != null) {
            log.info( "updateSiniestros " + siniestro.toStringLog());
            if(siniestro.getUuid()==null){
                siniestro.setUuid(UUID.randomUUID().toString());
            }
            if(siniestro.getCreated()==null){
                siniestro.setCreated(LocalDateTime.now());
            }
            return this.repository.save(siniestro);
        } else {
            String nombre = "El siniestro";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public void deleteSiniestros(Integer id) {
        log.info( "deleteSiniestros " + id);
        this.repository.deleteById(id);
    }

}
