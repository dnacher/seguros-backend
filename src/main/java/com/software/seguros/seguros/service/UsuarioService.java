package com.software.seguros.seguros.service;

import com.software.seguros.seguros.constantes.ConstantesErrores;
import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.exceptions.UAuthException;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.persistence.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final UsuarioRepository repository;
    private static final Integer LOG_ROUNDS = 10;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        this.repository.findAll().forEach(usuarios::add);
        log.info("getUsuarios");
        return usuarios;
    }

    public Usuario getUsuarioByUuid(String uuid) throws SegurosException {
        log.info( "getUsuarioById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El usuario uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Usuario getUsuarioById(Integer id) throws SegurosException {
        log.info( "getUsuarioById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El usuario id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
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
        log.logger.info("validaDatosUsuario {}", nombre);

        Usuario usuario = obtenerUsuarioPorNombre(nombre);

        if (usuario == null || !esPasswordValido(password, usuario.getPassword())) {
            log.logger.warn("Error de autenticación para el usuario: {}", nombre);
            throw new UAuthException(ConstantesErrores.ERROR_CREDENCIALES);
        }

        return usuario;
    }

    private Usuario obtenerUsuarioPorNombre(String nombre) {
        try {
            return repository.findByNombre(nombre);
        } catch (Exception e) {
            log.logger.error("Error al buscar el usuario por nombre: {}", nombre, e);
            return null;
        }
    }

    private boolean esPasswordValido(String rawPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(rawPassword, hashedPassword);
        } catch (Exception e) {
            log.logger.error("Error al verificar la contraseña", e);
            return false;
        }
    }


    public List<Usuario> saveUsuarios(List<Usuario> usuarios) throws SegurosException {
        List<Usuario> finalList = new ArrayList<>();
        this.repository.saveAll(usuarios).forEach(finalList::add);
        return finalList;
    }

    public void deleteUsuario(Integer id) {
        log.info("deleteUsuario " + id);
        this.repository.deleteById(id);
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
            return this.repository.save(usuario);
        } else {
            String nombre = "El usuario";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public void updateSinPass(String nombreUsuario, TipoUsuario tipoUsuario, Integer id){
        repository.updateUsuarioSinPass(nombreUsuario, tipoUsuario, id);
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }

    public Usuario setPassword(Usuario usuario) {
        String hashedPassword= BCrypt.hashpw(usuario.getPassword(),BCrypt.gensalt(LOG_ROUNDS));
        usuario.setPassword(hashedPassword);
        return repository.save(usuario);
    }

    public boolean isAdmin(String nombreUsuario) {
        return repository.findTipoUsuarioNombreByNombreUsuario(nombreUsuario).equals("admin");
    }

    public Codigo validarDatos(Usuario usuario) {
        if (usuario.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_USUARIO;
        } else if (usuario.getPassword().length() < 4) {
            return Codigo.PASS_USUARIO_MENOR_4;
        }
        return Codigo.OK;
    }
}
