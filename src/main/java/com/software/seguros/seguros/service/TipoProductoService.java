package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.TipoProductoDAO;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoProductoService {
    
    private final TipoProductoDAO tipoProductoDAO;
    
    public TipoProductoService(TipoProductoDAO tipoProductoDAO){
        this.tipoProductoDAO = tipoProductoDAO;
    }

    public List<TipoProducto> getTipoProductos(){
        return tipoProductoDAO.getTipoProductos();
    }

    public TipoProducto getTipoProductoById(Integer id){
        return tipoProductoDAO.getTipoProductosById(id);
    }

    public TipoProducto getTipoProductoByUuid(String uuid){
        return tipoProductoDAO.getTipoProductosByUuid(uuid);
    }

    public TipoProducto saveTipoProducto(TipoProducto tipoProducto){
        return tipoProductoDAO.saveTipoProductos(tipoProducto);
    }

    public TipoProducto updateTipoProducto(TipoProducto tipoProducto){
        return tipoProductoDAO.updateTipoProducto(tipoProducto);
    }

    public void deleteTipoProducto(TipoProducto tipoProducto){
        tipoProductoDAO.deleteTipoProducto(tipoProducto);
    }

}
