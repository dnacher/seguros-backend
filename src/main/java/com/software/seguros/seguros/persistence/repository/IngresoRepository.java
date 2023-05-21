package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {
    Optional<Ingreso> findByUuid(String uuid);
    Integer countByAnioAndMesAndBanco(Integer anio, Integer mes, Banco banco);
}
