package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.EstadoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPolizaRepository extends JpaRepository<EstadoPoliza, Integer> {
    Integer countByNombre(String nombre);
}
