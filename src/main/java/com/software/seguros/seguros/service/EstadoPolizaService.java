package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.EstadoPolizaDAO;
import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EstadoPolizaService {
    
    private final EstadoPolizaDAO estadoPolizaDAO;
    
    public EstadoPolizaService(EstadoPolizaDAO estadoPolizaDAO){
        this.estadoPolizaDAO= estadoPolizaDAO;
    }

    public List<EstadoPoliza> getEstadoPolizas(){
        return estadoPolizaDAO.getEstadoPolizas();
    }

    public EstadoPoliza getEstadoPolizaByUuid(String uuid){
        return estadoPolizaDAO.getEstadoPolizaByUuid(uuid);
    }

    public EstadoPoliza getEstadoPolizaById(Integer id){
        return estadoPolizaDAO.getEstadoPolizaById(id);
    }

    public EstadoPoliza saveEstadoPoliza(EstadoPoliza estadoPoliza){
        return estadoPolizaDAO.saveEstadoPoliza(estadoPoliza);
    }

    public EstadoPoliza updateEstadoPoliza(EstadoPoliza estadoPoliza){
        return estadoPolizaDAO.updateEstadoPoliza(estadoPoliza);
    }

    public void deleteEstadoPoliza(EstadoPoliza estadoPoliza){
        estadoPolizaDAO.deleteEstadoPoliza(estadoPoliza);
    }

}
