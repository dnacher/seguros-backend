package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.repository.BancoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BancoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final BancoRepository repository;
    
    public BancoDAO(BancoRepository repository){
        this.repository = repository;
    }

    public List<Banco> getBancos() {
        return repository.findAllByOrderByNombreAsc();
    }

    public Banco getBancoById(Integer id) throws SegurosException {
        log.info("getBancoById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("Este banco no existe", id);
                            log.error(msg);
                            return new SegurosException(msg);
                        });
    }

    public Banco saveBanco(Banco banco) throws SegurosException {
        log.info( "Banco Guardado " + banco.toStringLog());
        return this.repository.save(banco);
    }

    public List<Banco> saveBancos(List<Banco> policies) throws SegurosException {
        List<Banco> finalList = new ArrayList<>();
        this.repository
                .saveAll(policies)
                .forEach(
                        productType -> {
                            finalList.add(productType);
                        });
        return finalList;
    }

    public void deleteBanco(Banco banco) {
        log.info( "banco borrado " + banco.toStringLog());
        this.repository.delete(banco);
    }

    public Banco updateBanco(Banco banco) throws SegurosException {
        if (banco.getId() != null) {
            log.info( "banco actualizado " + banco.toStringLog());
            return this.repository.save(banco);
        } else {
            String msg = String.format("No se puede actualizar banco sin id asocicado");
            log.error( msg);
            throw new SegurosException(msg);
        }
    }

    public Integer countBancoByNombre(String nombre){
        return this.repository.countBancoByNombre(nombre);
    }
}
