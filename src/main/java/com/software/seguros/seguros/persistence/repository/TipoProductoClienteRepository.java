package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.model.TipoProductoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoProductoClienteRepository extends JpaRepository<TipoProductoCliente, Integer> {
    List<TipoProductoCliente> findByCliente(Cliente cliente);
}
