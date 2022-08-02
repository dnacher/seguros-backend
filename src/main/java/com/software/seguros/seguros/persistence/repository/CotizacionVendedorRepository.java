package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.CotizacionVendedor;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionVendedorRepository extends JpaRepository<CotizacionVendedor, Integer> {
    List<CotizacionVendedor> findByProducto(Producto producto);
    List<CotizacionVendedor> findByVendedor(Vendedor vendedor);
}
