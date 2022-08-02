package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.PermissionUserDAO;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<PermisoUsuario> findAllByUserType(TipoUsuario tipoUsuario){
        return permissionUserDAO.findAllByUserType(tipoUsuario);
    }

    public PermisoUsuario savePermissionUser(PermisoUsuario permisoUsuario){
        return permissionUserDAO.savePermissionUser(permisoUsuario);
    }

    public List<PermisoUsuario> savePermissionUserList(List<PermisoUsuario> listaPermisoUsuarios){
        List<PermisoUsuario> finalist = new ArrayList<>();
        listaPermisoUsuarios.stream().forEach(pu -> {
            finalist.add(permissionUserDAO.savePermissionUser(pu));
        });
        return finalist;
    }

    public PermisoUsuario updatePermissionUser(PermisoUsuario permisoUsuario){
        return permissionUserDAO.updatePermissionUser(permisoUsuario);
    }

    public void deletePermissionUser(PermisoUsuario permisoUsuario){
        permissionUserDAO.deletePermissionUser(permisoUsuario);
    }

    public void deleteByTipoUsuario(TipoUsuario tu){
        permissionUserDAO.deleteByTipoUsuario(tu);
    }

}
