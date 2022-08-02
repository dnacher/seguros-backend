package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.CuotasPolizaDAO;
import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CuotasPolizaService {
    
    private final CuotasPolizaDAO cuotasPolizaDAO;
    
    public CuotasPolizaService(CuotasPolizaDAO cuotasPolizaDAO){
        this.cuotasPolizaDAO = cuotasPolizaDAO;
    }

    public List<CuotasPoliza> getCuotasPolizas(){
        return cuotasPolizaDAO.getCuotasPolizas();
    }

    public CuotasPoliza getCuotasPolizaById(Integer id){
        return cuotasPolizaDAO.getCuotasPolizaById(id);
    }

    public CuotasPoliza saveCuotasPoliza(CuotasPoliza cuotasPoliza) throws SegurosException {
        return cuotasPolizaDAO.saveCuotasPoliza(cuotasPoliza);
    }

    public CuotasPoliza updateCuotasPoliza(CuotasPoliza cuotasPoliza){
        return cuotasPolizaDAO.updateCuotasPoliza(cuotasPoliza);
    }

    public void deleteCuotasPoliza(CuotasPoliza cuotasPoliza){
        cuotasPolizaDAO.deleteCuotasPoliza(cuotasPoliza);
    }

}
