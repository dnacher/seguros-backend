package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoSiniestroRepository extends JpaRepository<EstadoSiniestro, Integer> {
    Integer countByNombre(String nombre);
}
