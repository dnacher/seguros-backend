package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.EstadoSiniestroDAO;
import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EstadoSiniestroService {
    
    private final EstadoSiniestroDAO estadoSiniestroDAO;
    
    public EstadoSiniestroService(EstadoSiniestroDAO estadoSiniestroDAO){
        this.estadoSiniestroDAO= estadoSiniestroDAO;
    }

    public List<EstadoSiniestro> getEstadoSiniestros(){
        return estadoSiniestroDAO.getEstadoSiniestros();
    }

    public EstadoSiniestro getEstadoSiniestroById(Integer id){
        return estadoSiniestroDAO.getEstadoSiniestroById(id);
    }

    public EstadoSiniestro saveEstadoSiniestro(EstadoSiniestro estadoSiniestro){
        return estadoSiniestroDAO.saveEstadoSiniestro(estadoSiniestro);
    }

    public EstadoSiniestro updateEstadoSiniestro(EstadoSiniestro estadoSiniestro){
        return estadoSiniestroDAO.updateEstadoSiniestro(estadoSiniestro);
    }

    public void deleteEstadoSiniestro(EstadoSiniestro estadoSiniestro){
        estadoSiniestroDAO.deleteEstadoSiniestro(estadoSiniestro);
    }

}
