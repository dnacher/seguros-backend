package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermisoUsuarioRepository extends JpaRepository<PermisoUsuario, Integer> {
    Optional<PermisoUsuario> findByUuid(String uuid);
    List<PermisoUsuario> findAllByTipoUsuario_Id(Integer tipoUsuarioId);
    void deleteByTipoUsuario(TipoUsuario tipo);
}
