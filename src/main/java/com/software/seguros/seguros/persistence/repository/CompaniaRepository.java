package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Compania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompaniaRepository extends JpaRepository<Compania, Integer> {
    Optional<Compania> findByUuid(String uuid);
    List<Compania> findAllByOrderByNombreAsc();
    Integer countByNombre(String nombre);
}
