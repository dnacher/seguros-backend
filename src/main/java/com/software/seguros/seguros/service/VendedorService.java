package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.VendedorDAO;
import com.software.seguros.seguros.persistence.model.Vendedor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VendedorService {
    
    private final VendedorDAO vendedorDAO;
    
    public VendedorService(VendedorDAO vendedorDAO){
        this.vendedorDAO= vendedorDAO;
    }

    public List<Vendedor> getVendedores(){
        return vendedorDAO.getVendedores();
    }

    public Vendedor getVendedorByUuid(String uuid){
        return vendedorDAO.getVendedorByUuid(uuid);
    }

    public Vendedor getVendedorById(Integer id){
        return vendedorDAO.getVendedorById(id);
    }

    public Vendedor saveVendedor(Vendedor vendedore){
        return vendedorDAO.saveVendedor(vendedore);
    }

    public Vendedor updateVendedor(Vendedor vendedore){
        return vendedorDAO.updateVendedor(vendedore);
    }

    public void deleteVendedor(Vendedor vendedore){
        vendedorDAO.deleteVendedor(vendedore);
    }

}
