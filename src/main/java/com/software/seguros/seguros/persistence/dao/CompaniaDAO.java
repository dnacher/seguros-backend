package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.repository.CompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompaniaDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CompaniaRepository repository;

    @Autowired
    public CompaniaDAO(CompaniaRepository repository){
        this.repository= repository;
    }

    public List<Compania> getCompanias() {
        log.info( "getCompanias");
        return repository.findAllByOrderByNombreAsc();
    }

    public Compania getCompaniaByUuid(String uuid) throws SegurosException {
        log.info( "getCompania " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La compania con este id no existe ", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Compania getCompaniaById(Integer id) throws SegurosException {
        log.info( "getCompania " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La compania con este id no existe ", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Compania saveCompania(Compania compania) throws SegurosException {
        log.info( "guardar compania " + compania.toStringLog());
        return this.repository.save(compania);
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }

    public List<Compania> saveCompanias(List<Compania> companies) throws SegurosException {
        List<Compania> finalList = new ArrayList<>();
        this.repository
                .saveAll(companies)
                .forEach(
                        company -> {
                            finalList.add(company);
                        });
        return finalList;
    }

    public void deleteCompania(Compania compania) {
        log.info( "Borrar compania " + compania.toStringLog());
        this.repository.delete(compania);
    }

    public Compania updateCompania(Compania compania) throws SegurosException {
        if (compania.getId() != null) {
            log.info( "actualizar compania " + compania.toStringLog());
            return this.repository.save(compania);
        } else {
            String msg = String.format("No se puede actualizar sin Id asociada");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
    
}
