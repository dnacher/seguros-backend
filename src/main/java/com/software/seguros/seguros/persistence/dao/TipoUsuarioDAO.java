package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TipoUsuarioDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoUsuarioRepository repository;
    
    public TipoUsuarioDAO(TipoUsuarioRepository repository){
        this.repository = repository;
    }

    public List<TipoUsuario> getTipoUsuarios() {
        List<TipoUsuario> policies = new ArrayList<>();
        this.repository.findAll().forEach(userType -> policies.add(userType));
        log.info("getTipoUsuarios");
        return policies;
    }

    public TipoUsuario getTipoUsuarioByUuid(String uuid) throws SegurosException {
        log.info("getTipoUsuarioById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The userType uuid %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public TipoUsuario getTipoUsuarioById(Integer id) throws SegurosException {
        log.info("getTipoUsuarioById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The userType id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public TipoUsuario saveTipoUsuario(TipoUsuario tipoUsuario) throws SegurosException {
        log.info( "saveTipoUsuario " + tipoUsuario);
        return this.repository.save(tipoUsuario);
    }

    public List<TipoUsuario> saveTipoUsuarios(List<TipoUsuario> policies) throws SegurosException {
        List<TipoUsuario> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        userType -> {
                            finalList.add(userType);
                        });
        return finalList;
    }

    public void deleteTipoUsuario(TipoUsuario tipoUsuario) {
        log.info( "deleteTipoUsuario " + tipoUsuario.toStringLog());
        this.repository.delete(tipoUsuario);
    }

    public TipoUsuario updateTipoUsuario(TipoUsuario tipoUsuario) throws SegurosException {
        if (tipoUsuario.getId() != null) {
            log.info( "updateTipoUsuario " + tipoUsuario.toStringLog());
            if(tipoUsuario.getUuid()==null){
                tipoUsuario.setUuid(UUID.randomUUID().toString());
            }
            if(tipoUsuario.getCreated()==null){
                tipoUsuario.setCreated(LocalDateTime.now());
            }
            return this.repository.save(tipoUsuario);
        } else {
            String msg = String.format("Cannot update a userType without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre){
        log.info( "countByNombre " + nombre);
        return this.repository.countByNombre(nombre);
    }
}
