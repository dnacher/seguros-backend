package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.SiniestroDAO;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SiniestroService {
    
    private final SiniestroDAO siniestroDAO;
    
    public SiniestroService(SiniestroDAO siniestroDAO){
        this.siniestroDAO = siniestroDAO;
    }

    public List<Siniestro> getSiniestros(){
        return siniestroDAO.getSiniestros();
    }

    public Siniestro getSiniestroByUuid(String uuid){
        return siniestroDAO.getSiniestroByUuid(uuid);
    }

    public Siniestro getSiniestroById(Integer id){
        return siniestroDAO.getSiniestroById(id);
    }

    public Siniestro saveSiniestro(Siniestro siniestro){
        return siniestroDAO.saveSiniestro(siniestro);
    }

    public Siniestro updateSiniestro(Siniestro siniestro){
        return siniestroDAO.updateSiniestros(siniestro);
    }

    public void deleteSiniestro(Integer id){
        siniestroDAO.deleteSiniestros(id);
    }

    public List<Siniestro> findByPoliza(Integer polizaId){ return siniestroDAO.findByPoliza(polizaId); }

    public List<Siniestro> findByCliente(Integer clienteId){
        return siniestroDAO.findByCliente(clienteId);
    }

    public Codigo validarDatos(Siniestro siniestro) {
        if (siniestro.getNumeroSiniestro().isEmpty()) {
            return Codigo.NUMERO_SINIESTRO_VACIO;
        } else if(siniestroDAO.countByNumeroSiniestro(siniestro.getNumeroSiniestro())>0){
            return Codigo.NUMERO_SINIESTRO_EXISTE;
        } else if(siniestro.getPoliza()==null){
            return Codigo.POLIZA_NO_EXISTE;
        } else if(siniestro.getCliente()==null){
            return Codigo.CLIENTE_NO_EXISTE;
        } else if(siniestro.getEsDeducible() && siniestro.getImporteDeducible()==null){
            return Codigo.VERIFIQUE_DEDUCIBLE;
        }else if (siniestro.getEstadoSiniestro()==null){
            return Codigo.VERIFIQUE_ESTADO;
        }
        return Codigo.OK;
    }

}
