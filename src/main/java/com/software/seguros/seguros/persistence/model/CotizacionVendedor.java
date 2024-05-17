package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotizacion_vendedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CotizacionVendedor extends AbstractDomainEntity {

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
