package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
    Integer countByNombre(String nombre);
}
