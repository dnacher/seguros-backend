package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.repository.TipoUsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TipoUsuarioService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoUsuarioRepository repository;

    public TipoUsuarioService(TipoUsuarioRepository repository){
        this.repository = repository;
    }

    public List<TipoUsuario> getTipoUsuarios() {
        List<TipoUsuario> policies = new ArrayList<>();
        this.repository.findAll().forEach(policies::add);
        log.info("getTipoUsuarios");
        return policies;
    }

    public TipoUsuario getTipoUsuarioByUuid(String uuid) throws SegurosException {
        log.info("getTipoUsuarioById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El tipo usuario uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public TipoUsuario getTipoUsuarioById(Integer id) throws SegurosException {
        log.info("getTipoUsuarioById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El tipo usuario id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public TipoUsuario saveTipoUsuario(TipoUsuario tipoUsuario) throws SegurosException {
        log.info( "saveTipoUsuario " + tipoUsuario);
        return this.repository.save(tipoUsuario);
    }

    public List<TipoUsuario> saveTipoUsuarios(List<TipoUsuario> policies) throws SegurosException {
        List<TipoUsuario> finalList = new ArrayList<>();
        this.repository.saveAll(policies).forEach(finalList::add);
        return finalList;
    }

    public void deleteTipoUsuario(Integer id) {
        log.info( "deleteTipoUsuario " + id);
        this.repository.deleteById(id);
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
            String nombre = "El tipo usuario";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countByNombre(String nombre){
        log.info( "countByNombre " + nombre);
        return this.repository.countByNombre(nombre);
    }

    public Codigo validarDatos(TipoUsuario tipoUsuario) {
        if (tipoUsuario.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_TIPO_PRODUCTO;
        } else if(countByNombre(tipoUsuario.getNombre())>0){
            return Codigo.NOMBRE_TIPO_PRODUCTO_EXISTE;
        }
        return Codigo.OK;
    }

}
