package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.FormaPagoDAO;
import com.software.seguros.seguros.persistence.model.FormaPago;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FormaPagoService {
    
    private final FormaPagoDAO formaPagoDAO;
    
    public FormaPagoService(FormaPagoDAO formaPagoDAO){
        this.formaPagoDAO = formaPagoDAO;
    }

    public List<FormaPago> getFormaPagos(){
        return formaPagoDAO.getFormaPagos();
    }

    public FormaPago getFormaPagoById(Integer id){
        return formaPagoDAO.getFormaPagoById(id);
    }

    public FormaPago saveFormaPago(FormaPago formaPago) throws SegurosException {
        return formaPagoDAO.saveFormaPago(formaPago);
    }

    public FormaPago updateFormaPago(FormaPago formaPago){
        return formaPagoDAO.updateFormaPago(formaPago);
    }

    public void deleteFormaPago(FormaPago formaPago){
        formaPagoDAO.deleteFormaPago(formaPago);
    }

}
