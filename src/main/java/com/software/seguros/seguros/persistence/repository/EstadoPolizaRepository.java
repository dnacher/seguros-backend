package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoPolizaRepository extends JpaRepository<EstadoPoliza, Integer> {
    Optional<EstadoPoliza> findByUuid(String uuid);
    Integer countByNombre(String nombre);
}
