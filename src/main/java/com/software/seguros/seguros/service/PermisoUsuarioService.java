package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.PermisoUsuarioDAO;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PermisoUsuarioService {
    
    private final PermisoUsuarioDAO permissionUserDAO;
    
    public PermisoUsuarioService(PermisoUsuarioDAO permissionUserDAO){
        this.permissionUserDAO= permissionUserDAO;
    }

    public List<PermisoUsuario> getPermisoUsuarios(){
        return permissionUserDAO.getPermissionUsers();
    }

    public PermisoUsuario getPermisoUsuarioByUuid(String uuid){
        return permissionUserDAO.getPermissionUserByUuid(uuid);
    }

    public PermisoUsuario getPermisoUsuarioById(Integer id){
        return permissionUserDAO.getPermissionUserById(id);
    }

    public List<PermisoUsuario> findAllByTipoUsuario(Integer tipoUsuarioId){
        return permissionUserDAO.findAllByUserType(tipoUsuarioId);
    }

    public PermisoUsuario savePermisoUsuario(PermisoUsuario permisoUsuario){
        return permissionUserDAO.savePermissionUser(permisoUsuario);
    }

    public PermisoUsuario updatePermisoUsuario(PermisoUsuario permisoUsuario){
        return permissionUserDAO.updatePermissionUser(permisoUsuario);
    }

    public void deletePermisoUsuario(Integer id){
        permissionUserDAO.deletePermisoUsuario(id);
    }

    public void deleteByTipoUsuario(TipoUsuario tu){
        permissionUserDAO.deleteByTipoUsuario(tu);
    }

    public Codigo validarDatos(PermisoUsuario permisoUsuario) {
        return Codigo.OK;
    }

}
