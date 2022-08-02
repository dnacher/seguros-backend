package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.TipoProductoCliente;
import com.software.seguros.seguros.persistence.repository.TipoProductoClienteRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoProductoClienteDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoProductoClienteRepository repository;
    
    public TipoProductoClienteDAO(TipoProductoClienteRepository repository){
        this.repository = repository;
    }

    public List<TipoProductoCliente> getTipoProductoClientes() {
        List<TipoProductoCliente> tipoProductoClientes = new ArrayList<>();
        this.repository.findAll().forEach(tipoProductoCliente -> tipoProductoClientes.add(tipoProductoCliente));
        log.info(  "getTipoProductoClientes");
        return tipoProductoClientes;
    }

    public TipoProductoCliente getTipoProductoClienteById(Integer id) throws SegurosException {
        log.info( "getTipoProductoClienteById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public TipoProductoCliente saveTipoProductoCliente(TipoProductoCliente tipoProductoCliente) throws SegurosException {
        log.info( "saveTipoProductoCliente " + tipoProductoCliente.toStringLog());
        return this.repository.save(tipoProductoCliente);
    }

    public List<TipoProductoCliente> saveTipoProductoClientes(List<TipoProductoCliente> policies) throws SegurosException {
        List<TipoProductoCliente> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteTipoProductoCliente(TipoProductoCliente tipoProductoCliente) {
        log.info( "deleteTipoProductoCliente " + tipoProductoCliente.toStringLog());
        this.repository.delete(tipoProductoCliente);
    }

    public TipoProductoCliente updateTipoProductoCliente(TipoProductoCliente tipoProductoCliente) throws SegurosException {
        if (tipoProductoCliente.getId() != null) {
            log.info( "updateTipoProductoCliente " + tipoProductoCliente.toStringLog());
            return this.repository.save(tipoProductoCliente);
        } else {
            String msg = String.format("Cannot update a productType without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public List<TipoProductoCliente> findByCliente(Cliente cliente){
        log.info( "findByCliente " + cliente.toStringLog());
        return repository.findByCliente(cliente);
    }
}
