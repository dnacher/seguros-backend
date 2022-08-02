package com.software.seguros.seguros.service;

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

    public TipoUsuario getTipoUsuarioById(Integer id){
        return tipoUsuarioDAO.getTipoUsuarioById(id);
    }

    public TipoUsuario saveTipoUsuario(TipoUsuario tipoUsuario){
        return tipoUsuarioDAO.saveTipoUsuario(tipoUsuario);
    }

    public TipoUsuario updateTipoUsuario(TipoUsuario tipoUsuario){
        return tipoUsuarioDAO.updateTipoUsuario(tipoUsuario);
    }

    public void deleteTipoUsuario(TipoUsuario tipoUsuario) throws SQLIntegrityConstraintViolationException {
        tipoUsuarioDAO.deleteTipoUsuario(tipoUsuario);
    }

}
