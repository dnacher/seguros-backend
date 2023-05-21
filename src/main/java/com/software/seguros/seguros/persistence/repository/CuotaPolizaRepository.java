package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuotaPolizaRepository extends JpaRepository<CuotasPoliza, Integer> {
    Optional<CuotasPoliza> findByUuid(String uuid);
}
