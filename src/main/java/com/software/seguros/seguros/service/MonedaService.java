package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Moneda;
import com.software.seguros.seguros.persistence.repository.MonedaRepository;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MonedaService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final MonedaRepository repository;
    
    public MonedaService(MonedaRepository repository){
        this.repository = repository;
    }

    public List<Moneda> getMonedas() {
        List<Moneda> monedas = new ArrayList<>();
        this.repository.findAll().forEach(monedas::add);
        log.info("getMonedas");
        return monedas;
    }

    public Moneda getMonedaByUuid(String uuid) throws SegurosException {
        log.info( "getMoneda " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La moneda uuid %s no existe", uuid);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Moneda getMonedaById(Integer id) throws SegurosException {
        log.info( "getMonedaById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La moneda id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Moneda saveMoneda(Moneda moneda) throws SegurosException {
        log.info( "saveMoneda " + moneda.toStringLog());
        return this.repository.save(moneda);
    }

    public List<Moneda> saveMoneda(List<Moneda> policies) throws SegurosException {
        List<Moneda> finalList = new ArrayList<>();
        this.repository.saveAll(policies).forEach(finalList::add);
        return finalList;
    }

    public void deleteMoneda(Integer id) {
        log.info( "deleteMoneda " + id);
        this.repository.deleteById(id);
    }

    public Moneda updateMoneda(Moneda moneda) throws SegurosException {
        if (moneda.getId() != null) {
            log.info( "updateMoneda " + moneda.toStringLog());
            if(moneda.getUuid()==null){
                moneda.setUuid(UUID.randomUUID().toString());
            }
            if(moneda.getCreated()==null){
                moneda.setCreated(LocalDateTime.now());
            }
            return this.repository.save(moneda);
        } else {
            String nombre = "La moneda";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }

    public Codigo validarDatos(Moneda moneda){
        if (StringUtil.isEmpty(moneda.getNombre())) {
            return Codigo.FALTA_NOMBRE_MONEDA;
        }
        if(StringUtil.isEmpty(moneda.getSimbolo())) {
            return Codigo.FALTA_SIMBOLO_MONEDA;
        }
        if(countByNombre(moneda.getNombre())>0) {
            return Codigo.MONEDA_ASOCIADA_OTRO_REGISTRO;
        }
        return Codigo.OK;
    }

}
