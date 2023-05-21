package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Optional<Vendedor> findByUuid(String uuid);
}
