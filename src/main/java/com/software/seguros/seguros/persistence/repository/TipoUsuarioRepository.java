package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    Optional<TipoUsuario> findByUuid(String uuid);
    Integer countByNombre(String nombre);
}
