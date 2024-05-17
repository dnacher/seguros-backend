package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.DTO.BancoDTO;
import com.software.seguros.seguros.persistence.repository.BancoRepository;
import org.eclipse.jetty.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BancoService {

    private final LogManagerClass log = new LogManagerClass(getClass());

    private final BancoRepository repository;

    private final ModelMapper modelMapper;
    
    public BancoService(ModelMapper modelMapper, BancoRepository repository){
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public List<BancoDTO> getBancos() {
        log.info("getBancos");
        return repository.findAllByOrderByNombreAsc().stream()
                .map(banco -> modelMapper.map(banco, BancoDTO.class))
                .collect(Collectors.toList());
    }

    public Banco getBancoByUuid(String uuid) throws SegurosException {
        log.info("getBancoByUuid " + uuid);
        return this.repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("Este banco uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Banco getBancoById(Integer id) throws SegurosException {
        log.info("getBancoById " + id);
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("Este banco id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Banco saveBanco(Banco banco) throws SegurosException {
        log.info( "Banco Guardado " + banco.toStringLog());
        return this.repository.save(banco);
    }

    public List<Banco> saveBancos(List<Banco> policies) throws SegurosException {
        List<Banco> finalList = new ArrayList<>();
        finalList.addAll(this.repository.saveAll(policies));
        return finalList;
    }

    public void deleteBanco(Integer id) {
        log.info( "banco borrado " + id);
        this.repository.deleteById(id);
    }

    public Banco updateBanco(Banco banco) throws SegurosException {
        if (banco.getId() != null) {
            log.info( "banco actualizado " + banco.toStringLog());
            return this.repository.save(banco);
        } else {
            String nombre = "El banco";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error( msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public Integer countBancoByNombre(String nombre){
        return this.repository.countBancoByNombre(nombre);
    }

    public List<Banco> getByNombre(String nombre){
        Specification<Banco> spec = Specification.where(BancoRepository.BancoSpecifications.byNombre(nombre));

        return repository.findAll(spec);
    }

    public Codigo validarDatos(Banco banco){
        if (StringUtil.isEmpty(banco.getNombre())) {
            return Codigo.FALTA_NOMBRE_BANCO;
        }
        List<Banco> bancos = getByNombre(banco.getNombre());
        if(bancos!=null && bancos.isEmpty()) {
            return Codigo.NOMBRE_BANCO_EXISTE;
        }
        return Codigo.OK;
    }

}
