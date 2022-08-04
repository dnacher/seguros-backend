package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.PermissionUserDAO;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PermisoUsuarioService {
    
    private final PermissionUserDAO permissionUserDAO;
    
    public PermisoUsuarioService(PermissionUserDAO permissionUserDAO){
        this.permissionUserDAO= permissionUserDAO;
    }

    public List<PermisoUsuario> getPermisoUsuarios(){
        return permissionUserDAO.getPermissionUsers();
    }

    public PermisoUsuario getPermisoUsuarioById(Integer id){
        return permissionUserDAO.getPermissionUserById(id);
    }

    public List<PermisoUsuario> findAllByTipoUsuario(TipoUsuario tipoUsuario){
        return permissionUserDAO.findAllByUserType(tipoUsuario);
    }

    public PermisoUsuario savePermisoUsuario(PermisoUsuario permisoUsuario){
        return permissionUserDAO.savePermissionUser(permisoUsuario);
    }

    public PermisoUsuario updatePermisoUsuario(PermisoUsuario permisoUsuario){
        return permissionUserDAO.updatePermissionUser(permisoUsuario);
    }

    public void deletePermisoUsuario(PermisoUsuario permisoUsuario){
        permissionUserDAO.deletePermissionUser(permisoUsuario);
    }

    public void deleteByTipoUsuario(TipoUsuario tu){
        permissionUserDAO.deleteByTipoUsuario(tu);
    }

}
