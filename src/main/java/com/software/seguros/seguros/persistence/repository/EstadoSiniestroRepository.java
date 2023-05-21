package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.EstadoSiniestro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoSiniestroRepository extends JpaRepository<EstadoSiniestro, Integer> {
    Optional<EstadoSiniestro> findByUuid(String uuid);
    Integer countByNombre(String nombre);
}
