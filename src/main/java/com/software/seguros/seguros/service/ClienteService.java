package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.ClienteDAO;
import com.software.seguros.seguros.persistence.model.Cliente;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClienteService {
    
    private final ClienteDAO clienteDAO;
    
    public ClienteService(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> getClientes(){
        return clienteDAO.getClientes();
    }

    public Cliente getClienteByUuid(String uuid){
        return clienteDAO.getClienteByUuid(uuid);
    }

    public Cliente getClienteById(Integer id){
        return clienteDAO.getClienteById(id);
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteDAO.saveClient(cliente);
    }

    public Cliente updateCliente(Cliente cliente){
        return clienteDAO.updateClient(cliente);
    }

    public void deleteCliente(Integer id){
        clienteDAO.deleteCliente(id);
    }

    public List<Cliente> findAllByFechaNacimientoBetween(LocalDate fechaDesde, LocalDate fechaHasta){ return clienteDAO.findAllByFechaNacimientoBetween(fechaDesde, fechaHasta); }

    public List<Cliente> getAniversary(int diaInicio, int diaFinal, int mes){
        return clienteDAO.getAniversary(diaInicio, diaFinal, mes);
    }

    public Codigo validarDatos(Cliente cliente){
        if (StringUtil.isEmpty(cliente.getNombre())) {
            return Codigo.FALTA_NOMBRE_CLIENTE;
        }else if(StringUtil.isEmpty(cliente.getCedulaIdentidad())){
            return Codigo.FALTA_CEDULA_CLIENTE;
        }
        return Codigo.OK;
    }

}
