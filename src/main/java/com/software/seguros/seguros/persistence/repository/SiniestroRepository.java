package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiniestroRepository extends JpaRepository<Siniestro, Integer> {
    List<Siniestro> findByPoliza(Poliza poliza);
    List<Siniestro> findByCliente(Cliente cliente);
}
