package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String name);
    Integer countByNombre(String name);

    @Modifying
    @Query("UPDATE Usuario u " +
            "SET u.nombre=?1, " +
            "u.tipoUsuario=?2 " +
            "WHERE u.id=?3")
    void updateUsuarioSinPass(String nombreUsuario, TipoUsuario tipoUsuario, Integer id);
}
