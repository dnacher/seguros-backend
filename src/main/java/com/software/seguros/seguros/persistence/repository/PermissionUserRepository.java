package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionUserRepository extends JpaRepository<PermisoUsuario, Integer> {
    List<PermisoUsuario> findAllByTipoUsuario(TipoUsuario tipoUsuario);
    void deleteByTipoUsuario(TipoUsuario tipo);
}
