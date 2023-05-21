package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Integer> {
    Optional<Banco> findByUuid(String uuid);
    Integer countBancoByNombre(String nombre);
    List<Banco> findAllByOrderByNombreAsc();
}
