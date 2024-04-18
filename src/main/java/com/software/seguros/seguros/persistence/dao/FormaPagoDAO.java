package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.persistence.repository.FormaPagoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FormaPagoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final FormaPagoRepository repository;
    
    public FormaPagoDAO(FormaPagoRepository repository){
        this.repository = repository;
    }

    public List<FormaPago> getFormaPagos() {
        List<FormaPago> formaPagos = new ArrayList<>();
        this.repository.findAll().forEach(formaPago -> formaPagos.add(formaPago));
        log.info(  "getProductTypes");
        return formaPagos;
    }

    public FormaPago getFormaPagoByUuid(String uuid) throws SegurosException {
        log.info( "getProductType " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", uuid);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public FormaPago getFormaPagoById(Integer id) throws SegurosException {
        log.info( "getProductTypeById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("The productType id %s does not exist", id);
                            log.error( msg);
                            return new SegurosException(msg);
                        });
    }

    public FormaPago saveFormaPago(FormaPago formaPago) throws SegurosException {
        log.info( "saveProductType " + formaPago.toStringLog());
        return this.repository.save(formaPago);
    }

    public List<FormaPago> saveFormaPagos(List<FormaPago> policies) throws SegurosException {
        List<FormaPago> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteFormaPago(Integer id) {
        log.info( "deleteProductType " + id);
        this.repository.deleteById(id);
    }

    public FormaPago updateFormaPago(FormaPago formaPago) throws SegurosException {
        if (formaPago.getId() != null) {
            log.info( "updateProductType " + formaPago.toStringLog());
            if(formaPago.getUuid()==null){
                formaPago.setUuid(UUID.randomUUID().toString());
            }
            if(formaPago.getCreated()==null){
                formaPago.setCreated(LocalDateTime.now());
            }
            return this.repository.save(formaPago);
        } else {
            String msg = String.format("Cannot update a productType without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countByNombre(String nombre) {
        return repository.countByNombre(nombre);
    }

}
