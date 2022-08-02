package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.repository.ProductoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final ProductoRepository repository;
    
    public ProductoDAO(ProductoRepository repository){
        this.repository = repository;
    }

    public List<Producto> getProductos() {
        return repository.findAllByOrderByNombreAndTipoProductoAsc();
    }

    public Producto getProductoById(Integer id) throws SegurosException {
        log.info( "getProductoById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The product id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public List<Producto> findByCompania(Compania compania){
        log.info( "findByCompania " + compania.toStringLog());
        return repository.findByCompania(compania);
    }

    public List<Producto> findByTipoProducto(TipoProducto tipoProducto){
        log.info( "findByTipoProducto " + tipoProducto.toStringLog());
        return repository.findByTipoProducto(tipoProducto);
    }

    public Producto saveProducto(Producto producto) throws SegurosException {
        log.info( "saveProducto " + producto.toStringLog());
        return repository.save(producto);
    }

    public List<Producto> saveProductos(List<Producto> policies) throws SegurosException {
        List<Producto> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        product -> {
                            finalList.add(product);
                        });
        return finalList;
    }

    public void deleteProducto(Producto producto) {
        log.info( "deleteProducto " + producto.toStringLog());
        this.repository.delete(producto);
    }

    public Producto updateProducto(Producto producto) throws SegurosException {
        if (producto.getId() != null) {
            log.info( "updateProducto " + producto.toStringLog());
            return this.repository.save(producto);
        } else {
            String msg = String.format("Cannot update a product without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre){
        log.info( "countByNombre " + nombre);
        return this.repository.countByNombre(nombre);
    }
}
