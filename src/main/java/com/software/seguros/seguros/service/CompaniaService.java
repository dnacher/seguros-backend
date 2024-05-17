package com.software.seguros.seguros.service;

import com.software.seguros.seguros.constantes.ConstantesEtiquetas;
import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.repository.CompaniaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompaniaService {

    private final LogManagerClass log = new LogManagerClass(getClass());
    private final CompaniaRepository repository;
    
    public CompaniaService(CompaniaRepository repository){
        this.repository= repository;
    }

    public List<Compania> getCompanias() {
        log.info( "getCompanias");
        return repository.findAllByOrderByNombreAsc();
    }

    public Compania getCompaniaByUuid(String uuid) throws SegurosException {
        log.info( "getCompania " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La compañia uuid %s no existe ", uuid);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Compania getCompaniaById(Integer id) throws SegurosException {
        log.info( "getCompania " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La compañia id %s no existe ", id);
                            log.error( msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Compania saveCompania(Compania compania) throws SegurosException {
        log.info( "guardar compania " + compania.toStringLog());
        return this.repository.save(compania);
    }

    public Integer countByNombre(String nombre){
        return this.repository.countByNombre(nombre);
    }

    public List<Compania> saveCompanias(List<Compania> companies) throws SegurosException {
        List<Compania> finalList = new ArrayList<>();
    this.repository.saveAll(companies).forEach(finalList::add);
        return finalList;
    }

    public void deleteCompania(Integer id) {
        log.info( "Borrar compania " + id);
        this.repository.deleteById(id);
    }

    public Compania updateCompania(Compania compania) throws SegurosException {
        if (compania.getId() != null) {
            log.info( "actualizar compania " + compania.toStringLog());
            if(compania.getUuid()==null){
                compania.setUuid(UUID.randomUUID().toString());
            }
            if(compania.getCreated()==null){
                compania.setCreated(LocalDateTime.now());
            }
            return this.repository.save(compania);
        } else {
            String nombre = "La compañia";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Codigo validarDatos(Compania compania){
        if (compania.getNombre().equals(ConstantesEtiquetas.VACIO)) {
            return Codigo.FALTA_NOMBRE_COMPANIA;
        }
        return Codigo.OK;
    }

}
