package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.repository.TipoProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TipoProductoService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final TipoProductoRepository repository;

    public TipoProductoService(TipoProductoRepository repository){
        this.repository = repository;
    }

    public List<TipoProducto> getTipoProductos() {
        log.info( "getTipoProductos");
        return repository.findAllByOrderByNombre();
    }

    public TipoProducto getTipoProductoByUuid(String uuid) throws SegurosException {
        log.info( "getTipoProductosById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El tipo producto uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public TipoProducto getTipoProductoById(Integer id) throws SegurosException {
        log.info( "getTipoProductosById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("El tipo producto id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public TipoProducto saveTipoProducto(TipoProducto tipoProducto) throws SegurosException {
        log.info( "saveTipoProductos " + tipoProducto.toStringLog());
        return this.repository.save(tipoProducto);
    }

    public List<TipoProducto> saveTipoProductos(List<TipoProducto> policies) throws SegurosException {
        List<TipoProducto> finalList = new ArrayList<>();
        this.repository.saveAll(policies).forEach(finalList::add);
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
            String nombre = "El tipo producto";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countByNombre(String nombre){
        log.info( "countByNombre " + nombre);
        return this.repository.countByNombre(nombre);
    }

    public Codigo validarDatos(TipoProducto tipoProducto) {
        if (tipoProducto.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_TIPO_PRODUCTO;
        } else if(countByNombre(tipoProducto.getNombre())>0){
            return Codigo.NOMBRE_TIPO_PRODUCTO_EXISTE;
        }
        return Codigo.OK;
    }

}
