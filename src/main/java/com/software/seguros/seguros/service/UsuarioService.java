package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.UAuthException;
import com.software.seguros.seguros.persistence.dao.UsuarioDAO;
import com.software.seguros.seguros.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioService(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getUsuarios(){
        return usuarioDAO.getUsuarios();
    }

    public Usuario getUsuarioById(Integer id){ return usuarioDAO.getUsuarioById(id); }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioDAO.saveUsuario(usuario);
    }

    public Usuario validarUsuario(String name, String password) throws UAuthException { return usuarioDAO.validarUsuario(name, password); }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioDAO.updateUsuario(usuario);
    }

    public void updateSinPass(Usuario usuario){
        usuarioDAO.updateSinPass(usuario.getNombre(), usuario.getTipoUsuario(), usuario.getId());
    }

    public void deleteUsuario(Usuario usuario){
        usuarioDAO.deleteUsuario(usuario);
    }

}
