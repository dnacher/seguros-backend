package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.exceptions.UAuthException;
import com.software.seguros.seguros.persistence.dao.UsuarioDAO;
import com.software.seguros.seguros.persistence.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
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

    public Usuario getUsuarioByUuid(String uuid){ return usuarioDAO.getUsuarioByUuid(uuid); }

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

    public void deleteUsuario(Integer id){
        usuarioDAO.deleteUsuario(id);
    }

    public Usuario getUsuarioByNombre(String nombre) throws SegurosException {
        return this.usuarioDAO
                .getUsuarioByNombre(nombre);
    }

    public Usuario setPassword(Usuario usuario) {
        return usuarioDAO.setPassword(usuario);
    }

    public Codigo validarDatos(Usuario usuario) {
        if (usuario.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_USUARIO;
        } else if (usuario.getPassword().length() < 4) {
            return Codigo.PASS_USUARIO_MENOR_4;
        }
        return Codigo.OK;
    }

    public boolean isAdmin(String nombreUsuario) {
        return usuarioDAO.isAdmin(nombreUsuario);
    }
}
