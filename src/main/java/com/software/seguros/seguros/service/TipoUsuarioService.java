package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.TipoUsuarioDAO;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Transactional
public class TipoUsuarioService {
    
    private final TipoUsuarioDAO tipoUsuarioDAO;
    
    public TipoUsuarioService(TipoUsuarioDAO tipoUsuarioDAO){
        this.tipoUsuarioDAO = tipoUsuarioDAO;
    }

    public List<TipoUsuario> getTipoUsuarios(){
        return tipoUsuarioDAO.getTipoUsuarios();
    }

    public TipoUsuario getTipoUsuarioByUuid(String uuid){
        return tipoUsuarioDAO.getTipoUsuarioByUuid(uuid);
    }

    public TipoUsuario getTipoUsuarioById(Integer id){
        return tipoUsuarioDAO.getTipoUsuarioById(id);
    }

    public TipoUsuario saveTipoUsuario(TipoUsuario tipoUsuario){
        return tipoUsuarioDAO.saveTipoUsuario(tipoUsuario);
    }

    public TipoUsuario updateTipoUsuario(TipoUsuario tipoUsuario){
        return tipoUsuarioDAO.updateTipoUsuario(tipoUsuario);
    }

    public void deleteTipoUsuario(Integer id) {
        tipoUsuarioDAO.deleteTipoUsuario(id);
    }

    public Codigo validarDatos(TipoUsuario tipoUsuario) {
        if (tipoUsuario.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_TIPO_PRODUCTO;
        } else if(tipoUsuarioDAO.countByNombre(tipoUsuario.getNombre())>0){
            return Codigo.NOMBRE_TIPO_PRODUCTO_EXISTE;
        }
        return Codigo.OK;
    }

}
