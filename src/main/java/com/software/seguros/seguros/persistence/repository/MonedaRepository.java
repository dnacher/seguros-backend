package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
    Optional<Moneda> findByUuid(String uuid);
    Integer countByNombre(String nombre);
}
