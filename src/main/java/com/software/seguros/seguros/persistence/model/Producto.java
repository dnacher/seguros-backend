package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "producto")
public class Producto extends AbstractDomainEntity {

    public Producto(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "compania")
    private Compania compania;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_producto")
    private TipoProducto tipoProducto;

    @Column(name = "comision_nueva")
    private Double comisionNueva;

    @Column(name = "comision_renovacion")
    private Double comisionRenovacion;

    @Column(name = "fecha_comienzo")
    private Date fechaComienzo;

    @Column(name = "fecha_final")
    private Date fechaFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Double getComisionNueva() {
        return comisionNueva;
    }

    public void setComisionNueva(Double comisionNueva) {
        this.comisionNueva = comisionNueva;
    }

    public Double getComisionRenovacion() {
        return comisionRenovacion;
    }

    public void setComisionRenovacion(Double comisionRenovacion) {
        this.comisionRenovacion = comisionRenovacion;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public String getFechaComienzoToString() {
        return UtilsGeneral.getFechaFormato(fechaComienzo);
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public String getFechaFinalToString() {
        return UtilsGeneral.getFechaFormato(fechaFinal);
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringLog() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", compania=" + compania +
                ", tipoProducto=" + tipoProducto +
                ", comisionNueva=" + comisionNueva +
                ", comisionRenovacion=" + comisionRenovacion +
                ", fechaComienzo=" + fechaComienzo +
                ", fechaFinal=" + fechaFinal +
                '}';
    }
}
