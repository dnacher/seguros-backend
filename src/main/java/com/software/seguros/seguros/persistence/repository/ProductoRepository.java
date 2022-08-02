package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query( "SELECT p " +
            "FROM Producto p " +
            "ORDER BY p.nombre, p.tipoProducto.nombre")
    List<Producto> findAllByOrderByNombreAndTipoProductoAsc();


    Integer countByNombre(String nombre);
    List<Producto> findByCompania(Compania compania);
    List<Producto> findByTipoProducto(TipoProducto tipoProducto);
}
