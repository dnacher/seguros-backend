package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.persistence.repository.CotizacionVendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CotizacionVendedorDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CotizacionVendedorRepository repository;
    
    @Autowired
    public CotizacionVendedorDAO(CotizacionVendedorRepository repository){
        this.repository = repository;
    }

    public List<CotizacionVendedor> getCotizacionVendedores() {
        List<CotizacionVendedor> cotizacionVendedores = new ArrayList<>();
        this.repository.findAll().forEach(cotizacionVendedor -> cotizacionVendedores.add(cotizacionVendedor));
        log.info( "getCotizacionVendedores");
        return cotizacionVendedores;
    }

    public CotizacionVendedor getCotizacionVendedorByUuid(String uuid) throws SegurosException {
        log.info( "getCotizacionVendedorById " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cotizacion con este Id no existe", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public CotizacionVendedor getCotizacionVendedorById(Integer id) throws SegurosException {
        log.info( "getCotizacionVendedorById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cotizacion con este Id no existe", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public List<CotizacionVendedor> findByVendedor(Vendedor vendedor){
        log.info( "findByVendedor " + vendedor.toStringLog());
        return this.repository.findByVendedor(vendedor);
    }

    public List<CotizacionVendedor> findByProducto(Producto producto){
        log.info( "findByProducto " + producto.toStringLog());
        return this.repository.findByProducto(producto);
    }

    public CotizacionVendedor saveCotizacionVendedor(CotizacionVendedor cotizacionVendedor) throws SegurosException {
        log.info( "Guardar cotizacion " + cotizacionVendedor.toStringLog());
        return this.repository.save(cotizacionVendedor);
    }
    
    public List<CotizacionVendedor> saveCotizacionVendedores(List<CotizacionVendedor> cotizacionVendedores) throws SegurosException {
        List<CotizacionVendedor> finalList = new ArrayList<>();
        this.repository
                .saveAll(cotizacionVendedores)
                .forEach(
                        cotizacionVendedor -> {
                            finalList.add(cotizacionVendedor);
                        });
        return finalList;
    }

    public void deleteCotizacionVendedor(CotizacionVendedor cotizacionVendedor) {
        log.info( "Borrar cotizacion " + cotizacionVendedor.toStringLog());
        this.repository.delete(cotizacionVendedor);
    }

    public CotizacionVendedor updateCotizacionVendedor(CotizacionVendedor cotizacionVendedor) throws SegurosException {
        if (cotizacionVendedor.getId() != null) {
            log.info( "Actualizar cotizacion " + cotizacionVendedor.toStringLog());
            return this.repository.save(cotizacionVendedor);
        } else {
            String msg = String.format("No se puede actualizar cotizacion sin Id asociado");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
}
