package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.persistence.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        log.info( "getVendedores");
        return vendedores;
    }

    public Vendedor getVendedorById(Integer id) throws SegurosException {
        log.info( "getVendedor " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The vendedor id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
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

    public void deleteVendedor(Vendedor vendedor) {
        log.info( "deleteVendedor " + vendedor.toStringLog());
        this.repository.delete(vendedor);
    }

    public Vendedor updateVendedor(Vendedor vendedor) throws SegurosException {
        if (vendedor.getId() != null) {
            log.info( "updateVendedor " + vendedor.toStringLog());
            return this.repository.save(vendedor);
        } else {
            String msg = String.format("Cannot update a vendedor without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
}
