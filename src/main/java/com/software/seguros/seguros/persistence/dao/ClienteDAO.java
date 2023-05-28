package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ClienteDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final ClienteRepository repository;
    
    @Autowired
    public ClienteDAO(ClienteRepository repository){
        this.repository= repository;
    }

    public List<Cliente> getClientes() {
        return repository.findAllByOrderByNombreAscApellidoAsc();
    }

    public Cliente getClienteByUuid(String uuid) throws SegurosException {
        log.info( "getCliente " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El cliente con esta id no existe ", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Cliente getClienteById(Integer id) throws SegurosException {
        log.info( "getCliente " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El cliente con esta id no existe ", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Cliente saveClient(Cliente cliente) throws SegurosException {
        log.info( "Guardar cliente " + cliente.toStringLog());
        return this.repository.save(cliente);
    }
    
    public List<Cliente> saveClients(List<Cliente> clientes) throws SegurosException {
        List<Cliente> finalList = new ArrayList<>();
        this.repository
                .saveAll(clientes)
                .forEach(
                        client -> {
                            finalList.add(client);
                        });
        return finalList;
    }

    public void deleteClient(Cliente cliente) {
        log.info( "Borrar cliente " + cliente.toStringLog());
        this.repository.delete(cliente);
    }

    public Cliente updateClient(Cliente cliente) throws SegurosException {
        if (cliente.getId() != null) {
            log.info( "Actualizar cliente " + cliente.toStringLog());
            if(cliente.getUuid()==null){
                cliente.setUuid(UUID.randomUUID().toString());
            }
            if(cliente.getCreated()==null){
                cliente.setCreated(LocalDateTime.now());
            }
            return this.repository.save(cliente);
        } else {
            String msg = String.format("No se puede actualizar un cliente sin Id asociada" + cliente.toStringLog());
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public List<Cliente> findAllByFechaNacimientoBetween(Date fechaDesde, Date fechaHasta){
        log.info( "findAllByFechaNacimientoBetween" + fechaDesde + "-" + fechaHasta);
        return repository.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta);
    }

    public List<Cliente> getAniversary(int diaInicio, int diaFinal, int mes){
        log.info( "getAniversary");
        return repository.getAniversary(diaInicio, diaFinal, mes);
    }
}
