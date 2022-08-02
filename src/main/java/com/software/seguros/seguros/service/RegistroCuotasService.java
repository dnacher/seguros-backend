package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.RegsitroCuotasDAO;
import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegistroCuotasService {
    
    private final RegsitroCuotasDAO registroCuotasDAO;
    
    public RegistroCuotasService(RegsitroCuotasDAO registroCuotasDAO){
        this.registroCuotasDAO = registroCuotasDAO;
    }

    public List<RegistroCuotas> getRegistroCuotas(){
        return registroCuotasDAO.getRegistroCuotas();
    }

    public RegistroCuotas getRegistroCuotasById(Integer id){
        return registroCuotasDAO.getRegistroCuotasById(id);
    }

    public RegistroCuotas saveRegistroCuotas(RegistroCuotas registroCuotas) throws SegurosException {
        return registroCuotasDAO.saveRegistroCuotas(registroCuotas);
    }

    public RegistroCuotas updateRegistroCuotas(RegistroCuotas registroCuotas){
        return registroCuotasDAO.updateRegistroCuotas(registroCuotas);
    }

    public void deleteRegistroCuotas(RegistroCuotas registroCuotas){
        registroCuotasDAO.deleteRegistroCuotas(registroCuotas);
    }

    public List<RegistroCuotas> getRegistrosCuotasConCuotas(){
        return this.registroCuotasDAO.getRegistrosCuotasConCuotas();
    }

}
