package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.repository.TipoProductoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TipoProductoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoProductoRepository repository;
    
    public TipoProductoDAO(TipoProductoRepository repository){
        this.repository = repository;
    }

    public List<TipoProducto> getTipoProductos() {
        log.info( "getTipoProductos");
        return repository.findAllByOrderByNombre();
    }

    public TipoProducto getTipoProductosByUuid(String uuid) throws SegurosException {
        log.info( "getTipoProductosById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType uuid %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
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

    public void deleteTipoProducto(Integer id) {
        log.info( "deleteTipoProducto " + id);
        this.repository.deleteById(id);
    }

    public TipoProducto updateTipoProducto(TipoProducto tipoProducto) throws SegurosException {
        if (tipoProducto.getId() != null) {
            log.info( "updateTipoProducto " + tipoProducto.toStringLog());
            if(tipoProducto.getUuid()==null){
                tipoProducto.setUuid(UUID.randomUUID().toString());
            }
            if(tipoProducto.getCreated()==null){
                tipoProducto.setCreated(LocalDateTime.now());
            }
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
