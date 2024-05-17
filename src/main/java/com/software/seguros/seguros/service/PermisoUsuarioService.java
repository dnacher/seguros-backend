package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.repository.PermisoUsuarioRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PermisoUsuarioService {

  private final LogManagerClass log = new LogManagerClass(getClass());
  private final PermisoUsuarioRepository repository;

  public PermisoUsuarioService(PermisoUsuarioRepository repository) {
    this.repository = repository;
  }

  public List<PermisoUsuario> getPermisoUsuarios() {
    log.info("getPermisoUsuarios");
    return this.repository.findAll();
  }

  public PermisoUsuario getPermisoUsuarioByUuid(String uuid) throws SegurosException {
    log.info("getPermisoUsuario: " + uuid);
    return this.repository
        .findByUuid(uuid)
        .orElseThrow(
            () -> {
              String msg = String.format("El permiso de usuario uuid %s no existe", uuid);
              log.error(msg);
              return new SegurosException(HttpStatus.NOT_FOUND, msg);
            });
  }

  public PermisoUsuario getPermisoUsuarioById(Integer id) throws SegurosException {
    log.info("getPermissionUser: " + id);
    return this.repository
        .findById(id)
        .orElseThrow(
            () -> {
              String msg = String.format("El permiso de usuario id %s no existe", id);
              log.error(msg);
              return new SegurosException(HttpStatus.NOT_FOUND, msg);
            });
  }

  public List<PermisoUsuario> findAllByTipoUsuario(Integer tipoUsuarioId) {
    log.info("findAllByUserType: " + tipoUsuarioId);
    return this.repository.findAllByTipoUsuario_Id(tipoUsuarioId);
  }

  public PermisoUsuario savePermisoUsuario(PermisoUsuario permisoUsuario) throws SegurosException {
    log.info("savePermissionUser: " + permisoUsuario.toStringLog());
    return this.repository.save(permisoUsuario);
  }

  public List<PermisoUsuario> savePermisoUsuarios(List<PermisoUsuario> permisoUsuarios)
      throws SegurosException {
    List<PermisoUsuario> finalList = new ArrayList<>();
    this.repository.saveAll(permisoUsuarios).forEach(finalList::add);
    return finalList;
  }

  public void deletePermisoUsuario(Integer id) {
    log.info("deletePermisoUsuario " + id);
    this.repository.deleteById(id);
  }

  public PermisoUsuario updatePermisoUsuario(PermisoUsuario permisoUsuario)
      throws SegurosException {
    if (permisoUsuario.getId() != null) {
      log.info("updatePermissionUser " + permisoUsuario.toStringLog());
      if (permisoUsuario.getUuid() == null) {
        permisoUsuario.setUuid(UUID.randomUUID().toString());
      }
      if (permisoUsuario.getCreated() == null) {
        permisoUsuario.setCreated(LocalDateTime.now());
      }
      return this.repository.save(permisoUsuario);
    } else {
      String nombre = "El permiso";
      String msg = String.format("%s no se puede actualizar sin Id", nombre);
      log.error(msg);
      throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
    }
  }

  public void deleteByTipoUsuario(TipoUsuario tu) {
    repository.deleteByTipoUsuario(tu);
  }

  public Codigo validarDatos() {
    return Codigo.OK;
  }
}
