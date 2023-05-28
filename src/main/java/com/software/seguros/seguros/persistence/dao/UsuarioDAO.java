package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.constantes.ConstantesErrores;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.exceptions.UAuthException;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.persistence.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UsuarioDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final UsuarioRepository repository;
    private final Integer LOG_ROUNDS = 10;

    @Autowired
    public UsuarioDAO(UsuarioRepository repository){
        this.repository = repository;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        this.repository.findAll().forEach(user -> usuarios.add(user));
        log.info( "getUsuarios");
        return usuarios;
    }

    public Usuario getUsuarioByUuid(String uuid) throws SegurosException {
        log.info( "getUsuarioById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The user uuid %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Usuario getUsuarioById(Integer id) throws SegurosException {
        log.info( "getUsuarioById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The user id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Usuario getUsuarioByNombre(String nombre) throws SegurosException {
        log.info( "getUsuarioByNombre " + nombre);
        return this.repository
                .findByNombre(nombre);
    }

    public Usuario saveUsuario(Usuario usuario) throws SegurosException {
        log.info( "saveUsuario " + usuario);
        String hashedPassword= BCrypt.hashpw(usuario.getPassword(),BCrypt.gensalt(LOG_ROUNDS));
        usuario.setPassword(hashedPassword);
        return this.repository.save(usuario);
    }

    public Usuario validarUsuario(String nombre, String password) throws UAuthException {
        log.info( "validaDatosUsuario " + nombre);
        Usuario hashedUsuario = repository.findByNombre(nombre);
        if (hashedUsuario != null) {
          if (BCrypt.checkpw(password, hashedUsuario.getPassword())) {
            return hashedUsuario;
          }
        }
        log.warn(ConstantesErrores.ERROR_CREDENCIALES);
        throw new UAuthException(ConstantesErrores.ERROR_CREDENCIALES);
    }


    public List<Usuario> saveUsuarios(List<Usuario> usuarios) throws SegurosException {
        List<Usuario> finalList = new ArrayList<>();
        this.repository
                .saveAll(usuarios)
                .forEach(
                        user -> {
                            finalList.add(user);
                        });
        return finalList;
    }

    public void deleteUsuario(Usuario usuario) {
        log.info( "deleteUsuario " + usuario.toStringLog());
        this.repository.delete(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) throws SegurosException {
        if (usuario.getId() != null) {
            log.info( "updateUsuario " + usuario.toStringLog());
            if(usuario.getUuid()==null){
                usuario.setUuid(UUID.randomUUID().toString());
            }
            if(usuario.getCreated()==null){
                usuario.setCreated(LocalDateTime.now());
            }
            String hashedPassword= BCrypt.hashpw(usuario.getPassword(),BCrypt.gensalt(LOG_ROUNDS));
            usuario.setPassword(hashedPassword);
            return this.repository.save(usuario);
        } else {
            String msg = String.format("Cannot update a user without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public void updateSinPass(String nombreUsuario, TipoUsuario tipoUsuario, Integer id){
        repository.updateUsuarioSinPass(nombreUsuario, tipoUsuario, id);
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }
}
