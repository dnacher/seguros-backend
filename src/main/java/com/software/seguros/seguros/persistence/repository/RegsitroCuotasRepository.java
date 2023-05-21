package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.RegistroCuotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegsitroCuotasRepository extends JpaRepository<RegistroCuotas, Integer> {

    Optional<RegistroCuotas> findByUuid(String uuid);

    @Query("FROM RegistroCuotas rc " +
            "WHERE rc.poliza.cuotas>rc.numeroCuotasPagas")
    List<RegistroCuotas> getRegistrosCuotasConCuotas();
}
