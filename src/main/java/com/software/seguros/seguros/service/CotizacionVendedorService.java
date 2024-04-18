package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.CotizacionVendedorDAO;
import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CotizacionVendedorService {
    
    private final CotizacionVendedorDAO cotizacionVendedorDAO;
    
    public CotizacionVendedorService(CotizacionVendedorDAO cotizacionVendedorDAO){
        this.cotizacionVendedorDAO = cotizacionVendedorDAO;
    }

    public List<CotizacionVendedor> getCotizacionVendedores(){
        return cotizacionVendedorDAO.getCotizacionVendedores();
    }

    public CotizacionVendedor getCotizacionVendedorByUuid(String uuid){
        return cotizacionVendedorDAO.getCotizacionVendedorByUuid(uuid);
    }

    public CotizacionVendedor getCotizacionVendedorById(Integer id){
        return cotizacionVendedorDAO.getCotizacionVendedorById(id);
    }

    public CotizacionVendedor saveCotizacionVendedor(CotizacionVendedor cotizacionVendedor) throws SegurosException {
        return cotizacionVendedorDAO.saveCotizacionVendedor(cotizacionVendedor);
    }

    public List<CotizacionVendedor> findByProducto(Producto producto){
        return cotizacionVendedorDAO.findByProducto(producto);
    }

    public CotizacionVendedor updateCotizacionVendedor(CotizacionVendedor cotizacionVendedor){
        return cotizacionVendedorDAO.updateCotizacionVendedor(cotizacionVendedor);
    }

    public void deleteCotizacionVendedor(Integer id){
        cotizacionVendedorDAO.deleteCotizacionVendedor(id);
    }

    public List<CotizacionVendedor> findByVendedor(Vendedor vendedor){
        return cotizacionVendedorDAO.findByVendedor(vendedor);
    }

    public Codigo validarDatos(CotizacionVendedor cotizacionVendedor) {
        if (cotizacionVendedor.getVendedor()==null) {
            return Codigo.FALTA_VENDEDOR_COTIZACION;
        } else if (!UtilsGeneral.esPorcentaje(cotizacionVendedor.getComisionNueva().toString()) || !UtilsGeneral.esPorcentaje(cotizacionVendedor.getComisionRenovacion().toString())) {
            return Codigo.COMISION_PORCENTAJE;
        }else if(cotizacionVendedor.getFechaInicio()==null || cotizacionVendedor.getFechaFin()==null || cotizacionVendedor.getFechaFin().before(cotizacionVendedor.getFechaInicio())){
            return Codigo.FECHA_COMIENZO_FINAL_ORDEN;
        }
        return Codigo.OK;
    }
}
