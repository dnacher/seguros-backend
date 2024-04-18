package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.MonedaDAO;
import com.software.seguros.seguros.persistence.model.FormaPago;
import com.software.seguros.seguros.persistence.model.Moneda;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MonedaService {
    
    private final MonedaDAO monedaDAO;
    
    public MonedaService(MonedaDAO monedaDAO){
        this.monedaDAO= monedaDAO;
    }

    public List<Moneda> getMonedas(){
        return monedaDAO.getMonedas();
    }

    public Moneda getMonedaByUuid(String uuid){
        return monedaDAO.getMonedaByUuid(uuid);
    }

    public Moneda getMonedaById(Integer id){
        return monedaDAO.getMonedaById(id);
    }

    public Moneda saveMoneda(Moneda moneda){
        return monedaDAO.saveMoneda(moneda);
    }

    public Moneda updateMoneda(Moneda moneda){
        return monedaDAO.updateMoneda(moneda);
    }

    public void deleteMoneda(Integer id){
        monedaDAO.deleteMoneda(id);
    }

    public Codigo validarDatos(Moneda moneda){
        if (StringUtil.isEmpty(moneda.getNombre())) {
            return Codigo.FALTA_NOMBRE_MONEDA;
        }
        if(StringUtil.isEmpty(moneda.getSimbolo())) {
            return Codigo.FALTA_SIMBOLO_MONEDA;
        }
        if(monedaDAO.countByNombre(moneda.getNombre())>0) {
            return Codigo.MONEDA_ASOCIADA_OTRO_REGISTRO;
        }
        return Codigo.OK;
    }

}
