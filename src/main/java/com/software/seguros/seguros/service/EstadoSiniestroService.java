package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.EstadoSiniestroDAO;
import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import org.eclipse.jetty.util.StringUtil;
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

    public EstadoSiniestro getEstadoSiniestroByUuid(String uuid){
        return estadoSiniestroDAO.getEstadoSiniestroByUuid(uuid);
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

    public void deleteEstadoSiniestro(Integer id){
        estadoSiniestroDAO.deleteEstadoSiniestro(id);
    }

    public Codigo validarDatos(EstadoSiniestro estadoSiniestro){
        if (StringUtil.isEmpty(estadoSiniestro.getNombre())) {
            return Codigo.FALTA_NOMBRE_ESTADO_SINIESTRO;
        }
        if(estadoSiniestroDAO.countByNombre(estadoSiniestro.getNombre())>0) {
            return Codigo.ESTADO_SINIESTRO_EXISTE;
        }
        return Codigo.OK;
    }

}
