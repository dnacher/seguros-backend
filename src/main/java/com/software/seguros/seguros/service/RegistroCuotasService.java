package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
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

    public RegistroCuotas getRegistroCuotasByUuid(String uuid){
        return registroCuotasDAO.getRegistroCuotasByUuid(uuid);
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

    public void deleteRegistroCuotas(Integer id){
        registroCuotasDAO.deleteRegistroCuotas(id);
    }

    public List<RegistroCuotas> getRegistrosCuotasConCuotas(){
        return this.registroCuotasDAO.getRegistrosCuotasConCuotas();
    }

    public Codigo validarDatos(RegistroCuotas registroCuotas) {
        if(registroCuotas.getPoliza()==null) {
            return Codigo.REGISTRO_CUOTAS_SIN_POLIZA;
        }
        return Codigo.OK;
    }

}
