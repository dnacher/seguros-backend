package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.repository.TipoProductoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoProductoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoProductoRepository repository;
    
    public TipoProductoDAO(TipoProductoRepository repository){
        this.repository = repository;
    }

    public List<TipoProducto> getTipoProductos() {
        return repository.findAllByOrderByNombre();
    }

    public TipoProducto getTipoProductosById(Integer id) throws SegurosException {
        log.info( "getTipoProductosById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public TipoProducto saveTipoProductos(TipoProducto tipoProducto) throws SegurosException {
        log.info( "saveTipoProductos " + tipoProducto.toStringLog());
        return this.repository.save(tipoProducto);
    }

    public List<TipoProducto> saveTipoProductos(List<TipoProducto> policies) throws SegurosException {
        List<TipoProducto> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteTipoProducto(TipoProducto tipoProducto) {
        log.info( "deleteTipoProducto " + tipoProducto.toStringLog());
        this.repository.delete(tipoProducto);
    }

    public TipoProducto updateTipoProducto(TipoProducto tipoProducto) throws SegurosException {
        if (tipoProducto.getId() != null) {
            log.info( "updateTipoProducto " + tipoProducto.toStringLog());
            return this.repository.save(tipoProducto);
        } else {
            String msg = String.format("Cannot update a productType without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre){
        log.info( "countByNombre " + nombre);
        return this.repository.countByNombre(nombre);
    }
}
