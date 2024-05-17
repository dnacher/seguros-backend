package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByUuid(String uuid);

    @Query( "SELECT p " +
            "FROM Producto p " +
            "ORDER BY p.nombre, p.tipoProducto.nombre")
    List<Producto> findAllByOrderByNombreAndTipoProductoAsc();


    Integer countByNombre(String nombre);
    List<Producto> findByCompania_Id(Integer companiaId);
    List<Producto> findByTipoProducto_Id(Integer tipoProductoId);
}
