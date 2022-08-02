package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<Banco, Integer> {
    Integer countBancoByNombre(String nombre);
    List<Banco> findAllByOrderByNombreAsc();
}
