package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.IngresoDAO;
import com.software.seguros.seguros.persistence.model.Ingreso;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngresoService {
    
    private final IngresoDAO ingresoDAO;
    
    public IngresoService(IngresoDAO ingresoDAO){
        this.ingresoDAO = ingresoDAO;
    }

    public List<Ingreso> getIngresos(){
        return ingresoDAO.getIngresos();
    }

    public Ingreso getIngresoByUuid(String uuid){
        return ingresoDAO.getIngresoByUuid(uuid);
    }

    public Ingreso getIngresoById(Integer id){
        return ingresoDAO.getIngresoById(id);
    }

    public Ingreso saveIngreso(Ingreso ingreso){
        return ingresoDAO.saveIngreso(ingreso);
    }

    public Ingreso updateIngreso(Ingreso ingreso){
        return ingresoDAO.updateIngreso(ingreso);
    }

    public void deleteIngreso(Ingreso ingreso){
        ingresoDAO.deleteIngreso(ingreso);
    }

}
