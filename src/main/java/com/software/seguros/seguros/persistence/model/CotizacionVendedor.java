package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotizacion_vendedor")
public class CotizacionVendedor {

    public CotizacionVendedor(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "producto")
    private Producto producto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "vendedor")
    private Vendedor vendedor;

    @Column(name = "comision_nueva")
    private Integer comisionNueva;

    @Column(name = "comision_renovacion")
    private Integer comisionRenovacion;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getComisionRenovacion() {
        return comisionRenovacion;
    }

    public void setComisionRenovacion(Integer comisionRenovacion) {
        this.comisionRenovacion = comisionRenovacion;
    }

    public Integer getComisionNueva() {
        return comisionNueva;
    }

    public void setComisionNueva(Integer comision) {
        this.comisionNueva = comision;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public String getVendedorToString() {
        return vendedor.getNombreYApellido();
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public TipoProducto getTipoProducto() {
        return producto.getTipoProducto();
    }

    public Compania getCompania(){
        return producto.getCompania();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return vendedor.toString();
    }


    public String toStringLog() {
        return "CotizacionVendedor{" +
                "id=" + id +
                ", producto=" + producto +
                ", vendedor=" + vendedor +
                ", comisionNueva=" + comisionNueva +
                ", comisionRenovacion=" + comisionRenovacion +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
