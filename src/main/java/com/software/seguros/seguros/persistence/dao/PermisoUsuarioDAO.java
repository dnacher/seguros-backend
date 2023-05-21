package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.repository.PermisoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermisoUsuarioDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final PermisoUsuarioRepository repository;
    
    public PermisoUsuarioDAO(PermisoUsuarioRepository repository){
        this.repository = repository;
    }

    public List<PermisoUsuario> getPermissionUsers() {
        List<PermisoUsuario> permisoUsuarios = new ArrayList<>();
        this.repository.findAll().forEach(permisoUsuario -> permisoUsuarios.add(permisoUsuario));
        log.info( "getPermissionUsers");
        return permisoUsuarios;
    }

    public PermisoUsuario getPermissionUserByUuid(String uuid) throws SegurosException {
        log.info( "getPermisoUsuario: " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The permissionUser id %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public PermisoUsuario getPermissionUserById(Integer id) throws SegurosException {
        log.info( "getPermissionUser: " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The permissionUser id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public List<PermisoUsuario> findAllByUserType(TipoUsuario tipoUsuario) {
        log.info( "findAllByUserType: " + tipoUsuario.toStringLog());
        return this.repository.findAllByTipoUsuario(tipoUsuario);
    }

    public PermisoUsuario savePermissionUser(PermisoUsuario permisoUsuario) throws SegurosException {
        log.info( "savePermissionUser: " + permisoUsuario.toStringLog());
        return this.repository.save(permisoUsuario);
    }

    public List<PermisoUsuario> savePermissionUsers(List<PermisoUsuario> permisoUsuarios) throws SegurosException {
        List<PermisoUsuario> finalList = new ArrayList<>();
        this.repository
                .saveAll(permisoUsuarios)
                .forEach(
                        permisoUsuario -> {
                            finalList.add(permisoUsuario);
                        });
        return finalList;
    }

    public void deletePermissionUser(PermisoUsuario permisoUsuario) {
        log.info( "deletePermissionUser " + permisoUsuario.toStringLog());
        this.repository.delete(permisoUsuario);
    }

    public void deleteByTipoUsuario(TipoUsuario tu){
        repository.deleteByTipoUsuario(tu);
    }

    public PermisoUsuario updatePermissionUser(PermisoUsuario permisoUsuario) throws SegurosException {
        if (permisoUsuario.getId() != null) {
            log.info( "updatePermissionUser " + permisoUsuario.toStringLog());
            return this.repository.save(permisoUsuario);
        } else {
            String msg = String.format("Cannot update a permission User without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
}
