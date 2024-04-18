package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.repository.CompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void deleteCompania(Integer id) {
        log.info( "Borrar compania " + id);
        this.repository.deleteById(id);
    }

    public Compania updateCompania(Compania compania) throws SegurosException {
        if (compania.getId() != null) {
            log.info( "actualizar compania " + compania.toStringLog());
            if(compania.getUuid()==null){
                compania.setUuid(UUID.randomUUID().toString());
            }
            if(compania.getCreated()==null){
                compania.setCreated(LocalDateTime.now());
            }
            return this.repository.save(compania);
        } else {
            String msg = String.format("No se puede actualizar sin Id asociada");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
    
}
