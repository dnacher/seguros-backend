package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.repository.PolizaRepository;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTO;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTOInt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class PolizaDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final PolizaRepository repository;
    
    public PolizaDAO(PolizaRepository repository){
        this.repository = repository;
    }

    public List<Poliza> getPolizas() {
        return repository.findAllByOrderByIdDesc();
    }

    public Poliza getPolizaByUuid(String uuid) throws SegurosException {
        log.info( "getPolizaById " + uuid);
        return repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La poliza uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Poliza getPolizaById(Integer id) throws SegurosException {
        log.info( "getPolizaById " + id);
        return repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La poliza id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public List<Poliza> findByCliente(Integer clienteId){
        log.info( "findByCliente " + clienteId);
        return repository.findByCliente_Id(clienteId);
    }

    public List<Poliza> findByClienteAndAndEstadoNombreOrEstadoNombre(Integer clienteId, String estadoNuevo, String estadoRenovacion){
        log.info( "findByClienteAndAndEstado_NombreOrEstado_Nombre " + clienteId);
        return repository.findByClienteAndEstado_NombreOrEstado_Nombre(clienteId, estadoNuevo, estadoRenovacion);
    }

    public List<Poliza> findByClienteAndAndEstadoNombre(Integer clienteId, String estadoNuevo){
        log.info( "findByClienteAndAndEstado_Nombre " + clienteId);
        return repository.findByClienteAndEstado_Nombre(clienteId, estadoNuevo);
    }

    public List<PolizaDTO> getTotalPrimaByFechasGroupByProductos(Date desde, Date hasta){
        log.info( "getTotalPrimaByFechasGroupByProductos " + desde + " " + hasta);
        return repository.getTotalPrimaByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getTotalPremioByFechasGroupByProductos(Date desde, Date hasta){
        log.info( "getTotalPremioByFechasGroupByProductos " + desde + " " + hasta);
        return repository.getTotalPremioByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getCountProductos(Date desde, Date hasta){
        log.info( "getCountProductos " + desde + " " + hasta);
        return repository.getCountProductos(desde, hasta);
    }

    public List<Poliza> getPolizasByFecha(Date desde, Date hasta){
        log.info( "getPolizasByFecha " + desde + " " + hasta);
        return repository.getPolizasByFecha(desde, hasta);
    }

    public List<Poliza> getPolizasVencimientoByFecha(Date desde, Date hasta){
        log.info( "getPolizasVencimientoByFecha " + desde + " " + hasta);
        return repository.getPolizasVencimientoByFecha(desde, hasta);
    }

    public List<PolizaDTOInt> getSUMPrimaProductos(Date desde, Date hasta){
        log.info( "getSUMPrimaProductos " + desde + " " + hasta);
        return repository.getSUMPrimaProductos(desde, hasta);
    }

    public List<PolizaDTOInt> getPolizasComisionesByFecha(Date desde, Date hasta){
        log.info( "getPolizasComisionesByFecha " + desde + " " + hasta);
        return repository.getPolizasComisionesByFecha(desde, hasta);
    }

    public Poliza savePoliza(Poliza poliza) throws SegurosException {
        log.info( "savePoliza " + poliza.toStringLog());
        return repository.save(poliza);
    }

    public List<Poliza> savePolizas(List<Poliza> policies) throws SegurosException {
        List<Poliza> finalList = new ArrayList<>();
        repository
                .saveAll(policies)
                .forEach(finalList::add);
        return finalList;
    }

    public Poliza updatePoliza(Poliza poliza) throws SegurosException {
        if (poliza.getId() != null) {
            log.info( "updatePoliza " + poliza.toStringLog());
            if(poliza.getUuid()==null){
                poliza.setUuid(UUID.randomUUID().toString());
            }
            if(poliza.getCreated()==null){
                poliza.setCreated(LocalDateTime.now());
            }
            return repository.save(poliza);
        } else {
            String nombre = "La poliza";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public void deletePoliza(Integer id) {
        log.info( "deletePoliza " + id);
        repository.deleteById(id);
    }

}
