package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.CuotasPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuotaPolizaRepository extends JpaRepository<CuotasPoliza, Integer> {
}
