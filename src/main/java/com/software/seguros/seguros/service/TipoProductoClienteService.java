package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.TipoProductoClienteDAO;
import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.TipoProductoCliente;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoProductoClienteService {
    
    private final TipoProductoClienteDAO tipoProductoClienteDAO;
    
    public TipoProductoClienteService(TipoProductoClienteDAO tipoProductoClienteDAO){
        this.tipoProductoClienteDAO = tipoProductoClienteDAO;
    }

    public List<TipoProductoCliente> getTipoProductoClientes(){
        return tipoProductoClienteDAO.getTipoProductoClientes();
    }

    public TipoProductoCliente getTipoProductoClienteById(Integer id){
        return tipoProductoClienteDAO.getTipoProductoClienteById(id);
    }

    public TipoProductoCliente saveTipoProductoCliente(TipoProductoCliente tipoProductoCliente){
        return tipoProductoClienteDAO.saveTipoProductoCliente(tipoProductoCliente);
    }

    public TipoProductoCliente updateTipoProductoCliente(TipoProductoCliente tipoProductoCliente){
        return tipoProductoClienteDAO.updateTipoProductoCliente(tipoProductoCliente);
    }

    public void deleteTipoProductoCliente(TipoProductoCliente tipoProductoCliente){
        tipoProductoClienteDAO.deleteTipoProductoCliente(tipoProductoCliente);
    }

    public List<TipoProductoCliente> findByCliente(Cliente cliente){
        return tipoProductoClienteDAO.findByCliente(cliente);
    }

}
