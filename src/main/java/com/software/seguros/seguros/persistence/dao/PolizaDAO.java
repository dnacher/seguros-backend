package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.repository.PolizaRepository;
import com.software.seguros.seguros.service.DTO.PolizaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Poliza getPolizaById(Integer id) throws SegurosException {
        log.info( "getPolizaById " + id);
        return repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The policy id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public Poliza savePoliza(Poliza poliza) throws SegurosException {
        log.info( "savePoliza " + poliza.toStringLog());
        return repository.save(poliza);
    }

    public List<Poliza> savePolizas(List<Poliza> policies) throws SegurosException {
        List<Poliza> finalList = new ArrayList<>();
        repository
                .saveAll(policies)
                .forEach(
                        poliza -> {
                            finalList.add(poliza);
                        });
        return finalList;
    }

    public void deletePoliza(Poliza poliza) {
        log.info( "deletePoliza " + poliza.toStringLog());
        repository.delete(poliza);
    }

    public Poliza updatePoliza(Poliza poliza) throws SegurosException {
        if (poliza.getId() != null) {
            log.info( "updatePoliza " + poliza.toStringLog());
            return repository.save(poliza);
        } else {
            String msg = String.format("Cannot update a policy without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }
    public List<Poliza> findByCliente(Cliente cliente){
        log.info( "findByCliente " + cliente.toStringLog());
        return repository.findByCliente(cliente);
    }

    public List<Poliza> findByClienteAndAndEstado_NombreOrEstado_Nombre(Cliente cliente, String estadoNuevo, String estadoRenovacion){
        log.info( "findByClienteAndAndEstado_NombreOrEstado_Nombre " + cliente.toStringLog());
        return repository.findByClienteAndEstado_NombreOrEstado_Nombre(cliente, estadoNuevo, estadoRenovacion);
    }

    public List<Poliza> findByClienteAndAndEstado_Nombre(Cliente cliente, String estadoNuevo){
        log.info( "findByClienteAndAndEstado_Nombre " + cliente.toStringLog());
        return repository.findByClienteAndEstado_Nombre(cliente, estadoNuevo);
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

    public List<PolizaDTO> getSUMPrimaProductos(Date desde, Date hasta){
        log.info( "getSUMPrimaProductos " + desde + " " + hasta);
        return repository.getSUMPrimaProductos(desde, hasta);
    }

    public List<PolizaDTO> getPolizasComisionesByFecha(Date desde, Date hasta){
        log.info( "getPolizasComisionesByFecha " + desde + " " + hasta);
        return repository.getPolizasComisionesByFecha(desde, hasta);
    }

}
