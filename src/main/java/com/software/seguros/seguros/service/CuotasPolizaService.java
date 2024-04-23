package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.CuotasPolizaDAO;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import org.eclipse.jetty.util.StringUtil;
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

    public CuotasPoliza getCuotasPolizaByUuid(String uuid){
        return cuotasPolizaDAO.getCuotasPolizaByUuid(uuid);
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

    public void deleteCuotasPoliza(Integer id){
        cuotasPolizaDAO.deleteCuotasPoliza(id);
    }

    public Codigo validarDatos(CuotasPoliza cuotasPoliza) {
        if (cuotasPoliza.getPoliza()==null) {
            return Codigo.CUOTA_POLIZA_FALTA_POLIZA;
        }
        return Codigo.OK;
    }

}
