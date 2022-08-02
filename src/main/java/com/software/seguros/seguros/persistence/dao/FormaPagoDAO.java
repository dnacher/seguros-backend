package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.persistence.repository.FormaPagoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteFormaPago(FormaPago formaPago) {
        log.info( "deleteProductType " + formaPago.toStringLog());
        this.repository.delete(formaPago);
    }

    public FormaPago updateFormaPago(FormaPago formaPago) throws SegurosException {
        if (formaPago.getId() != null) {
            log.info( "updateProductType " + formaPago.toStringLog());
            return this.repository.save(formaPago);
        } else {
            String msg = String.format("Cannot update a productType without an Id");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

}
