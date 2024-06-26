package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CotizacionVendedorRepository extends JpaRepository<CotizacionVendedor, Integer> {
    Optional<CotizacionVendedor> findByUuid(String uuid);
    List<CotizacionVendedor> findByProducto_Id(Integer productoId);
    List<CotizacionVendedor> findByVendedor_Id(Integer vendedorId);
}
