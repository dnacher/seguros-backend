package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.BancoDAO;
import com.software.seguros.seguros.persistence.model.Banco;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BancoService {
    
    private final BancoDAO bancoDAO;
    
    public BancoService(BancoDAO bancoDAO){
        this.bancoDAO = bancoDAO;
    }

    public List<Banco> getBancos(){
        return bancoDAO.getBancos();
    }

    public Banco getBancoByUuid(String uuid){
        return bancoDAO.getBancoByUuid(uuid);
    }

    public Banco getBancoById(Integer id){
        return bancoDAO.getBancoById(id);
    }

    public Banco saveBanco(Banco banco) throws SegurosException {
        return bancoDAO.saveBanco(banco);
    }

    public Banco updateBanco(Banco banco){
        return bancoDAO.updateBanco(banco);
    }

    public void deleteBanco(Banco banco){
        bancoDAO.deleteBanco(banco);
    }

    public Integer countBancoByNombre(String nombre){ return bancoDAO.countBancoByNombre(nombre); }

    public List<Banco> getByNombre(String nombre){
        return bancoDAO.getByNombre(nombre);
    }

}
