package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.dao.BancoDAO;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.DTO.BancoDTO;
import org.eclipse.jetty.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BancoService {
    
    private final BancoDAO bancoDAO;

    private final ModelMapper modelMapper;
    
    public BancoService(BancoDAO bancoDAO, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.bancoDAO = bancoDAO;
    }

    public List<BancoDTO> getBancos(){
        return bancoDAO.getBancos()
                .stream()
                .map(banco -> modelMapper.map(banco, BancoDTO.class))
                .collect(Collectors.toList());
    }

    public BancoDTO getBancoByUuid(String uuid){
        return modelMapper.map(bancoDAO.getBancoByUuid(uuid), BancoDTO.class);
    }

    public BancoDTO getBancoById(Integer id){
        return modelMapper.map(bancoDAO.getBancoById(id), BancoDTO.class);
    }

    public BancoDTO saveBanco(Banco banco) throws SegurosException {
        return modelMapper.map(bancoDAO.saveBanco(banco), BancoDTO.class);
    }

    public BancoDTO updateBanco(Banco banco){
        return modelMapper.map(bancoDAO.updateBanco(banco), BancoDTO.class);
    }

    public void deleteBanco(Integer id){
        bancoDAO.deleteBanco(id);
    }

    public Integer countBancoByNombre(String nombre){ return bancoDAO.countBancoByNombre(nombre); }

    public List<BancoDTO> getByNombre(String nombre){
        return bancoDAO.getByNombre(nombre).stream().map(banco -> modelMapper.map(banco, BancoDTO.class)).collect(Collectors.toList());
    }

    public Codigo validarDatos(Banco banco){
        if (StringUtil.isEmpty(banco.getNombre())) {
            return Codigo.FALTA_NOMBRE_BANCO;
        }
        List<Banco> bancos = bancoDAO.getByNombre(banco.getNombre());
        if(bancos!=null && bancos.size()>0) {
            return Codigo.NOMBRE_BANCO_EXISTE;
        }
        return Codigo.OK;
    }

}
