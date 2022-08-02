package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    Integer countByNombre(String nombre);
}
