package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUuid(String uuid);
    Usuario findByNombre(String name);
    Integer countByNombre(String name);

    @Modifying
    @Query(value = "UPDATE Usuario u " +
            "SET u.nombre=?1, " +
            "u.tipoUsuario=?2 " +
            "WHERE u.id=?3")
    void updateUsuarioSinPass(String nombreUsuario, TipoUsuario tipoUsuario, Integer id);

    @Query(value = "SELECT u.tipoUsuario.nombre " +
            "FROM Usuario u " +
            "WHERE u.nombre=?1")
    String findTipoUsuarioNombreByNombreUsuario(String nombreUsuario);
}
