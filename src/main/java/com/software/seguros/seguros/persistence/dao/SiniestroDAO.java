package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import com.software.seguros.seguros.persistence.repository.SiniestroRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public Siniestro getSiniestroById(Integer id) throws SegurosException {
        log.info( "getSiniestroById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The crash id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Siniestro saveSiniestro(Siniestro siniestro) throws SegurosException {
        log.info( "saveSiniestro " + siniestro.toStringLog());
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

    public void deleteSiniestros(Siniestro siniestro) {
        log.info( "deleteSiniestros " + siniestro.toStringLog());
        this.repository.delete(siniestro);
    }

    public Siniestro updateSiniestros(Siniestro siniestro) throws SegurosException {
        if (siniestro.getId() != null) {
            log.info( "updateSiniestros " + siniestro.toStringLog());
            return this.repository.save(siniestro);
        } else {
            String msg = String.format("Cannot update a crash without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public List<Siniestro> findByPoliza(Poliza poliza){
        log.info( "findByPoliza: " + poliza.toStringLog());
        return this.repository.findByPoliza(poliza);
    }

    public List<Siniestro> findByCliente(Cliente cliente){
        log.info( "findByCliente: " + cliente.getNombreYApellido() + " " + cliente.getCedulaIdentidad());
        return this.repository.findByCliente(cliente);
    }
}
