package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.Ingreso;
import com.software.seguros.seguros.persistence.repository.IngresoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngresoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final IngresoRepository repository;
    
    public IngresoDAO(IngresoRepository repository){
        this.repository = repository;
    }

    public List<Ingreso> getIngresos() {
        List<Ingreso> ingresos = new ArrayList<>();
        this.repository.findAll().forEach(ingreso -> ingresos.add(ingreso));
        log.info( "getIngreso");
        return ingresos;
    }

    public Ingreso getIngresoByUuid(String uuid) throws SegurosException {
        log.info( "getIngreso " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The income id %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Ingreso getIngresoById(Integer id) throws SegurosException {
        log.info( "getIngresoById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The income id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Ingreso saveIngreso(Ingreso ingreso) throws SegurosException {
        log.info( "saveIngreso " + ingreso.toStringLog());
        return this.repository.save(ingreso);
    }

    public List<Ingreso> saveIngresos(List<Ingreso> ingresos) throws SegurosException {
        List<Ingreso> finalList = new ArrayList<>();
        this.repository
                .saveAll(ingresos)
                .forEach(
                        ingreso -> {
                            finalList.add(ingreso);
                        });
        return finalList;
    }

    public void deleteIngreso(Ingreso ingreso) {
        log.info( "deleteIngreso " + ingreso.toStringLog());
        this.repository.delete(ingreso);
    }

    public Ingreso updateIngreso(Ingreso ingreso) throws SegurosException {
        if (ingreso.getId() != null) {
            log.info( "updateIngreso " + ingreso.toStringLog());
            return this.repository.save(ingreso);
        } else {
            String msg = String.format("Cannot update a income without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByAnioAndMesAndBanco(Integer anio, Integer mes, Banco banco){
        log.info( "countByAnioAndMesAndBanco " + anio + " " + mes  + " " + banco.getNombre());
        return repository.countByAnioAndMesAndBanco(anio, mes, banco);
    }
}
