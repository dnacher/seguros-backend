package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.persistence.repository.CotizacionVendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                            String msg = String.format("La cotizacion uuid %s no existe", uuid);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public CotizacionVendedor getCotizacionVendedorById(Integer id) throws SegurosException {
        log.info( "getCotizacionVendedorById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La cotizacion id %s no existe", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public List<CotizacionVendedor> findByVendedor(Integer vendedorId){
        log.info( "findByVendedor " + vendedorId);
        return this.repository.findByVendedor_Id(vendedorId);
    }

    public List<CotizacionVendedor> findByProducto(Integer productoId){
        log.info( "findByProducto " + productoId);
        return this.repository.findByProducto_Id(productoId);
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

    public void deleteCotizacionVendedor(Integer id) {
        log.info( "Borrar cotizacion " + id);
        this.repository.deleteById(id);
    }

    public CotizacionVendedor updateCotizacionVendedor(CotizacionVendedor cotizacionVendedor) throws SegurosException {
        if (cotizacionVendedor.getId() != null) {
            log.info( "Actualizar cotizacion " + cotizacionVendedor.toStringLog());
            if(cotizacionVendedor.getUuid()==null){
                cotizacionVendedor.setUuid(UUID.randomUUID().toString());
            }
            if(cotizacionVendedor.getCreated()==null){
                cotizacionVendedor.setCreated(LocalDateTime.now());
            }
            return this.repository.save(cotizacionVendedor);
        } else {
            String nombre = "La cotizacion";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }
}
