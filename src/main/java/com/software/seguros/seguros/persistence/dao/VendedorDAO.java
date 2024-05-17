package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.persistence.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class VendedorDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final VendedorRepository repository;
    
    @Autowired
    public VendedorDAO(VendedorRepository repository){
        this.repository= repository;
    }

    public List<Vendedor> getVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        this.repository.findAll().forEach(vendedor -> vendedores.add(vendedor));
        log.info("getVendedores");
        return vendedores;
    }

    public Vendedor getVendedorByUuid(String uuid) throws SegurosException {
        log.info( "getVendedor " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El vendedor uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Vendedor getVendedorById(Integer id) throws SegurosException {
        log.info( "getVendedor " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El vendedor id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Vendedor saveVendedor(Vendedor vendedor) throws SegurosException {
        log.info( "saveVendedor " + vendedor.toStringLog());
        return this.repository.save(vendedor);
    }
    
    public List<Vendedor> saveVendedores(List<Vendedor> vendedores) throws SegurosException {
        List<Vendedor> finalList = new ArrayList<>();
        this.repository
                .saveAll(vendedores)
                .forEach(
                        vendedor -> {
                            finalList.add(vendedor);
                        });
        return finalList;
    }

    public void deleteVendedor(Integer id) {
        log.info( "deleteVendedor " + id);
        this.repository.deleteById(id);
    }

    public Vendedor updateVendedor(Vendedor vendedor) throws SegurosException {
        if (vendedor.getId() != null) {
            log.info( "updateVendedor " + vendedor.toStringLog());
            if(vendedor.getUuid()==null){
                vendedor.setUuid(UUID.randomUUID().toString());
            }
            if(vendedor.getCreated()==null){
                vendedor.setCreated(LocalDateTime.now());
            }
            return this.repository.save(vendedor);
        } else {
            String nombre = "El vendedor";
            String msg = String.format("%s No se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }
}
