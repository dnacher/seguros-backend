package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByUuid(String uuid);
    List<Cliente> findAllByFechaNacimientoBetween(Date fechaDesde, Date fechaHasta);
    List<Cliente> findAllByOrderByNombreAscApellidoAsc();
    @Query(value = "SELECT * FROM Cliente c WHERE (DAY(c.fecha_nacimiento) >= ?1 AND DAY(c.fecha_nacimiento) <= ?2) AND ?3 = MONTH(c.fecha_nacimiento)", nativeQuery = true)
    List<Cliente> getAniversary(int diaInicio, int diaFinal, int mes);
}
