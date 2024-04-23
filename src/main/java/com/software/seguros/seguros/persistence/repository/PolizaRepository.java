package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Poliza;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTO;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTOInt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Integer> {
    Optional<Poliza> findByUuid(String uuid);
    List<Poliza> findByCliente_Id(Integer clienteId);
    List<Poliza> findAllByOrderByIdDesc();

    @Query("FROM Poliza p " +
            "WHERE p.cliente.id= ?1 " +
            "AND " +
            "(p.estado.nombre=?2 OR p.estado.nombre=?3)")
    List<Poliza> findByClienteAndEstado_NombreOrEstado_Nombre(Integer clienteId, String estadoNuevo, String estadoRenovacion);

    @Query("FROM Poliza p WHERE p.cliente.id= ?1 AND p.estado.nombre=?2")
    List<Poliza> findByClienteAndEstado_Nombre(Integer clienteId, String estadoNuevo);

    @Query( "SELECT new com.software.seguros.seguros.persistence.model.DTO.PolizaDTO(SUM(p.premio), p.producto, p.tipoProducto, p.compania, p.cliente, v)" +
            "FROM Poliza p " +
            "LEFT JOIN Vendedor v   ON p.vendedor=v " +
            "WHERE p.comienzo >=?1 " +
            "AND p.comienzo <= ?2 " +
            "GROUP BY p.producto, v")
    List<PolizaDTO> getTotalPremioByFechasGroupByProductos(Date desde, Date hasta);

    @Query( "SELECT new com.software.seguros.seguros.persistence.model.DTO.PolizaDTO(SUM(p.prima), p.producto, p.tipoProducto, p.compania, p.cliente, v)" +
            "FROM Poliza p " +
            "LEFT JOIN Vendedor v   ON p.vendedor=v " +
            "WHERE p.comienzo >=?1 " +
            "AND p.comienzo <= ?2 " +
            "GROUP BY p.producto, v")
    List<PolizaDTO> getTotalPrimaByFechasGroupByProductos(Date desde, Date hasta);

    @Query("SELECT new com.software.seguros.seguros.persistence.model.DTO.PolizaDTO((COUNT(p.tipoProducto) * 1.0), p.tipoProducto) " +
            "FROM Poliza p " +
            "WHERE p.comienzo >=?1 " +
            "AND p.comienzo <= ?2 " +
            "GROUP BY p.tipoProducto")
    List<PolizaDTO> getCountProductos(Date desde, Date hasta);


    @Query("SELECT p " +
            "FROM Poliza p " +
            "WHERE p.comienzo >=?1 " +
            "AND p.comienzo <= ?2")
    List<Poliza> getPolizasByFecha(Date desde, Date hasta);

    @Query("SELECT p " +
            "FROM Poliza p " +
            "WHERE p.vencimiento >=?1 " +
            "AND p.vencimiento <= ?2")
    List<Poliza> getPolizasVencimientoByFecha(Date desde, Date hasta);

  @Query("SELECT SUM(p.prima) as total, p.tipoProducto as tipoProducto, p.compania as compania "
          + "FROM Poliza p "
          + "WHERE p.comienzo >=?1 "
          + "AND p.comienzo <= ?2 "
          + "GROUP BY p.tipoProducto "
          + "HAVING SUM(p.prima)>0")
  List<PolizaDTOInt> getSUMPrimaProductos(Date desde, Date hasta);

    @Query("SELECT p.compania as compania, p.cerradoPor as cerradoPor, SUM(p.comisionValor) as comisionValor, p.producto as producto, p.moneda.simbolo as moneda " +
            "FROM Poliza p " +
            "WHERE p.comienzo >=?1 " +
            "AND p.comienzo <= ?2 " +
            "GROUP BY p.cerradoPor, p.compania, p.producto")
    List<PolizaDTOInt> getPolizasComisionesByFecha(Date desde, Date hasta);
}
