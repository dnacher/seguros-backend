package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.MonedaDAO;
import com.software.seguros.seguros.persistence.model.Moneda;
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

    public void deleteMoneda(Moneda moneda){
        monedaDAO.deleteMoneda(moneda);
    }

}
