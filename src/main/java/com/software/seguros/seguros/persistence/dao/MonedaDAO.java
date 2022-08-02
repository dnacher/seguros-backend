package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Moneda;
import com.software.seguros.seguros.persistence.repository.MonedaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonedaDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final MonedaRepository repository;
    
    public MonedaDAO(MonedaRepository repository){
        this.repository = repository;
    }

    public List<Moneda> getMonedas() {
        List<Moneda> monedas = new ArrayList<>();
        this.repository.findAll().forEach(moneda -> monedas.add(moneda));
        log.info(  "getMonedas");
        return monedas;
    }

    public Moneda getMonedaById(Integer id) throws SegurosException {
        log.info( "getMonedaById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The Moneda id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Moneda saveMoneda(Moneda moneda) throws SegurosException {
        log.info( "saveMoneda " + moneda.toStringLog());
        return this.repository.save(moneda);
    }

    public List<Moneda> saveMoneda(List<Moneda> policies) throws SegurosException {
        List<Moneda> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteMoneda(Moneda moneda) {
        log.info( "deleteMoneda " + moneda.toStringLog());
        this.repository.delete(moneda);
    }

    public Moneda updateMoneda(Moneda moneda) throws SegurosException {
        if (moneda.getId() != null) {
            log.info( "updateMoneda " + moneda.toStringLog());
            return this.repository.save(moneda);
        } else {
            String msg = String.format("Cannot update a Moneda without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }
}
