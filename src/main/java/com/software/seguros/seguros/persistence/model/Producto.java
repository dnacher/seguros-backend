package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "producto")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Producto extends AbstractDomainEntity {

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
    private LocalDate fechaComienzo;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;


    public String getFechaComienzoToString() {
        return UtilsGeneral.getFechaFormato(fechaComienzo);
    }

    public String getFechaFinalToString() {
        return UtilsGeneral.getFechaFormato(fechaFinal);
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
