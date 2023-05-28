package com.software.seguros.seguros.persistence.dao;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.repository.BancoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BancoDAO {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final BancoRepository repository;

    public BancoDAO(BancoRepository repository){
        this.repository = repository;
    }

    public List<Banco> getBancos() {
        log.info("getBancos");
        return repository.findAllByOrderByNombreAsc();
    }
    public Banco getBancoByUuid(String uuid) throws SegurosException {
        log.info("getBancoByUuid " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("Este banco no existe", uuid);
                            log.error(msg);
                            return new SegurosException(msg);
                        });
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
            if(banco.getUuid()==null){
                banco.setUuid(UUID.randomUUID().toString());
            }
            if(banco.getCreated()==null){
                banco.setCreated(LocalDateTime.now());
            }
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
