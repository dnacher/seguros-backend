package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import com.software.seguros.seguros.persistence.repository.EstadoSiniestroRepository;
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
public class EstadoSiniestroService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final EstadoSiniestroRepository repository;
    
    public EstadoSiniestroService(EstadoSiniestroRepository repository){
        this.repository = repository;
    }

    public List<EstadoSiniestro> getEstadoSiniestros() {
        List<EstadoSiniestro> estadoSiniestros = new ArrayList<>();
        this.repository.findAll().forEach(estadoSiniestros::add);
        log.info("getEstadoSiniestros");
        return estadoSiniestros;
    }

    public EstadoSiniestro getEstadoSiniestroByUuid(String uuid) throws SegurosException {
        log.info( "getEstadoSiniestro " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El estado siniestro uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public EstadoSiniestro getEstadoSiniestroById(Integer id) throws SegurosException {
        log.info( "getEstadoSiniestroById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El estado siniestro id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public EstadoSiniestro saveEstadoSiniestro(EstadoSiniestro estadoSiniestro) throws SegurosException {
        log.info( "saveEstadoSiniestro " + estadoSiniestro.toStringLog());
        return this.repository.save(estadoSiniestro);
    }

    public List<EstadoSiniestro> saveEstadoSiniestro(List<EstadoSiniestro> policies) throws SegurosException {
        List<EstadoSiniestro> finalList = new ArrayList<>();
        this.repository.saveAll(policies).forEach(finalList::add);
        return finalList;
    }

    public void deleteEstadoSiniestro(Integer id) {
        log.info( "deleteEstadoSiniestro " + id);
        this.repository.deleteById(id);
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
            String nombre = "El estado";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }

    public Codigo validarDatos(EstadoSiniestro estadoSiniestro){
        if (StringUtil.isEmpty(estadoSiniestro.getNombre())) {
            return Codigo.FALTA_NOMBRE_ESTADO_SINIESTRO;
        }
        if(countByNombre(estadoSiniestro.getNombre())>0) {
            return Codigo.ESTADO_SINIESTRO_EXISTE;
        }
        return Codigo.OK;
    }

}
