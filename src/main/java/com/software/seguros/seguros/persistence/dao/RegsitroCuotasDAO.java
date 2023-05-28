package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import com.software.seguros.seguros.persistence.repository.RegsitroCuotasRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RegsitroCuotasDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final RegsitroCuotasRepository repository;
    
    public RegsitroCuotasDAO(RegsitroCuotasRepository repository){
        this.repository = repository;
    }

    public List<RegistroCuotas> getRegistroCuotas() {
        List<RegistroCuotas> registroCuotass = new ArrayList<>();
        this.repository.findAll().forEach(registroCuotas -> registroCuotass.add(registroCuotas));
        log.info(  "getRegistroCuotas");
        return registroCuotass;
    }

    public RegistroCuotas getRegistroCuotasByUuid(String uuid) throws SegurosException {
        log.info( "getRegsitroCuotasById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public RegistroCuotas getRegistroCuotasById(Integer id) throws SegurosException {
        log.info( "getRegsitroCuotasById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public RegistroCuotas saveRegistroCuotas(RegistroCuotas registroCuotas) throws SegurosException {
        log.info( "saveRegistroCuotas " + registroCuotas.toStringLog());
        return this.repository.save(registroCuotas);
    }

    public List<RegistroCuotas> saveRegistroCuotas(List<RegistroCuotas> policies) throws SegurosException {
        List<RegistroCuotas> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteRegistroCuotas(RegistroCuotas registroCuotas) {
        log.info( "deleteRegistroCuotas " + registroCuotas.toStringLog());
        this.repository.delete(registroCuotas);
    }

    public RegistroCuotas updateRegistroCuotas(RegistroCuotas registroCuotas) throws SegurosException {
        if (registroCuotas.getId() != null) {
            log.info( "updateRegistroCuotas " + registroCuotas.toStringLog());
            if(registroCuotas.getUuid()==null){
                registroCuotas.setUuid(UUID.randomUUID().toString());
            }
            if(registroCuotas.getCreated()==null){
                registroCuotas.setCreated(LocalDateTime.now());
            }
            return this.repository.save(registroCuotas);
        } else {
            String msg = String.format("Cannot update a RegistroCuotas without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public List<RegistroCuotas> getRegistrosCuotasConCuotas(){
        log.info( "getRegistrosCuotasConCuotas ");
        return this.repository.getRegistrosCuotasConCuotas();
    }
}
