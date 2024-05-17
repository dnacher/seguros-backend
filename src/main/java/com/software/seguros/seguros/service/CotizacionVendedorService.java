package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.repository.CotizacionVendedorRepository;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CotizacionVendedorService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CotizacionVendedorRepository repository;
    
    public CotizacionVendedorService(CotizacionVendedorRepository repository){
        this.repository = repository;
    }

    public List<CotizacionVendedor> getCotizacionVendedores() {
        List<CotizacionVendedor> cotizacionVendedores = new ArrayList<>();
        repository.findAll().forEach(cotizacionVendedores::add);
        log.info( "getCotizacionVendedores");
        return cotizacionVendedores;
    }

    public CotizacionVendedor getCotizacionVendedorByUuid(String uuid) throws SegurosException {
        log.info( "getCotizacionVendedorById " + uuid);
        return repository
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
        return repository
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
        return repository.findByVendedor_Id(vendedorId);
    }

    public List<CotizacionVendedor> findByProducto(Integer productoId){
        log.info( "findByProducto " + productoId);
        return repository.findByProducto_Id(productoId);
    }

    public CotizacionVendedor saveCotizacionVendedor(CotizacionVendedor cotizacionVendedor) throws SegurosException {
        log.info( "Guardar cotizacion " + cotizacionVendedor.toStringLog());
        return repository.save(cotizacionVendedor);
    }

    public List<CotizacionVendedor> saveCotizacionVendedores(List<CotizacionVendedor> cotizacionVendedores) throws SegurosException {
        List<CotizacionVendedor> finalList = new ArrayList<>();
        repository.saveAll(cotizacionVendedores).forEach(finalList::add);
        return finalList;
    }

    public void deleteCotizacionVendedor(Integer id) {
        log.info( "Borrar cotizacion " + id);
        repository.deleteById(id);
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
            return repository.save(cotizacionVendedor);
        } else {
            String nombre = "La cotizacion";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Codigo validarDatos(CotizacionVendedor cotizacionVendedor) {
        if (cotizacionVendedor.getVendedor()==null) {
            return Codigo.FALTA_VENDEDOR_COTIZACION;
        } else if (!UtilsGeneral.esPorcentaje(cotizacionVendedor.getComisionNueva().toString()) || !UtilsGeneral.esPorcentaje(cotizacionVendedor.getComisionRenovacion().toString())) {
            return Codigo.COMISION_PORCENTAJE;
        }else if(cotizacionVendedor.getFechaInicio()==null || cotizacionVendedor.getFechaFin()==null || cotizacionVendedor.getFechaFin().before(cotizacionVendedor.getFechaInicio())){
            return Codigo.FECHA_COMIENZO_FINAL_ORDEN;
        }
        return Codigo.OK;
    }
}
