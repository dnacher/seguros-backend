package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.repository.ClienteRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {

  private final LogManagerClass log = new LogManagerClass(getClass());
  private final ClienteRepository repository;

  public ClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  public List<Cliente> getClientes() {
    return repository.findAllByOrderByNombreAscApellidoAsc();
  }

  public Cliente getClienteByUuid(String uuid) throws SegurosException {
    log.info("getCliente " + uuid);
    return this.repository
        .findByUuid(uuid)
        .orElseThrow(
            () -> {
              String msg = String.format("El cliente uuid %s no existe ", uuid);
              log.error(msg);
              return new SegurosException(HttpStatus.NOT_FOUND, msg);
            });
  }

  public Cliente getClienteById(Integer id) throws SegurosException {
    log.info("getCliente " + id);
    return this.repository
        .findById(id)
        .orElseThrow(
            () -> {
              String msg = String.format("El cliente id %s no existe ", id);
              log.error(msg);
              return new SegurosException(HttpStatus.NOT_FOUND, msg);
            });
  }

  public Cliente saveCliente(Cliente cliente) throws SegurosException {
    log.info("Guardar cliente " + cliente.toStringLog());
    return this.repository.save(cliente);
  }

  public List<Cliente> saveClientes(List<Cliente> clientes) throws SegurosException {
    List<Cliente> finalList = new ArrayList<>();
    this.repository.saveAll(clientes).forEach(finalList::add);
    return finalList;
  }

  public void deleteCliente(Integer id) {
    log.info("Borrar cliente " + id);
    this.repository.deleteById(id);
  }

  public Cliente updateCliente(Cliente cliente) throws SegurosException {
    if (cliente.getId() != null) {
      log.info("Actualizar cliente " + cliente.toStringLog());
      if (cliente.getUuid() == null) {
        cliente.setUuid(UUID.randomUUID().toString());
      }
      if (cliente.getCreated() == null) {
        cliente.setCreated(LocalDateTime.now());
      }
      return this.repository.save(cliente);
    } else {
      String nombre = "El cliente";
      String msg = String.format("%s no se puede actualizar sin Id", nombre);
      log.error(msg);
      throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
    }
  }

  public List<Cliente> findAllByFechaNacimientoBetween(LocalDate fechaDesde, LocalDate fechaHasta) {
    log.info("findAllByFechaNacimientoBetween" + fechaDesde + "-" + fechaHasta);
    return repository.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta);
  }

  public List<Cliente> getAniversary(int diaInicio, int diaFinal, int mes) {
    log.info("getAniversary");
    return repository.getAniversary(diaInicio, diaFinal, mes);
  }

  public Codigo validarDatos(Cliente cliente) {
    if (StringUtil.isEmpty(cliente.getNombre())) {
      return Codigo.FALTA_NOMBRE_CLIENTE;
    } else if (StringUtil.isEmpty(cliente.getCedulaIdentidad())) {
      return Codigo.FALTA_CEDULA_CLIENTE;
    }
    return Codigo.OK;
  }
}
