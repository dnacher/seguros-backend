package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.FormaPagoDAO;
import com.software.seguros.seguros.persistence.model.FormaPago;
import org.eclipse.jetty.util.StringUtil;
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

    public FormaPago getFormaPagoByUuid(String uuid){
        return formaPagoDAO.getFormaPagoByUuid(uuid);
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

    public void deleteFormaPago(Integer id){
        formaPagoDAO.deleteFormaPago(id);
    }

    public Codigo validarDatos(FormaPago formaPago){
        if (StringUtil.isEmpty(formaPago.getNombre())) {
            return Codigo.FALTA_NOMBRE_ESTADO_SINIESTRO;
        }
        if(formaPagoDAO.countByNombre(formaPago.getNombre())>0) {
            return Codigo.ESTADO_SINIESTRO_EXISTE;
        }
        return Codigo.OK;
    }

}
