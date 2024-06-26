package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.Siniestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiniestroRepository extends JpaRepository<Siniestro, Integer> {
    Optional<Siniestro> findByUuid(String uuid);
    List<Siniestro> findByPoliza_Id(Integer polizaId);
    List<Siniestro> findByCliente_Id(Integer clienteId);
    Integer countByNumeroSiniestro(String numeroSiniestro);
}
