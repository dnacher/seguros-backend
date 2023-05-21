package com.software.seguros.seguros.service;

import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.CompaniaDAO;
import com.software.seguros.seguros.persistence.model.Compania;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompaniaService {
    
    private final CompaniaDAO companiaDAO;
    
    public CompaniaService(CompaniaDAO companiaDAO){
        this.companiaDAO = companiaDAO;
    }

    public List<Compania> getCompanias(){
        return companiaDAO.getCompanias();
    }

    public Compania getCompaniaByUuid(String uuid){
        return companiaDAO.getCompaniaByUuid(uuid);
    }

    public Compania getCompaniaById(Integer id){
        return companiaDAO.getCompaniaById(id);
    }

    public Compania saveCompania(Compania compania) throws SegurosException {
        return companiaDAO.saveCompania(compania);
    }

    public Compania updateCompania(Compania compania){
        return companiaDAO.updateCompania(compania);
    }

    public void deleteCompania(Compania compania){
        companiaDAO.deleteCompania(compania);
    }

}
