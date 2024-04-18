package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
    Optional<FormaPago> findByUuid(String uuid);
    Integer countByNombre(String nombre);
}
