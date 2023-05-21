package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cuotas_poliza")
public class CuotasPoliza extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "poliza")
    private Poliza poliza;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "fecha_registrado")
    private Date fechaRegistrado;

    public CuotasPoliza(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaRegistrado() {
        return fechaRegistrado;
    }

    public void setFechaRegistrado(Date fechaRegistrado) {
        this.fechaRegistrado = fechaRegistrado;
    }

    public String toStringLog() {
        return "CuotasPoliza{" +
                "id=" + id +
                ", poliza=" + poliza +
                ", numeroCuota=" + numeroCuota +
                ", fechaRegistrado=" + fechaRegistrado +
                '}';
    }
}
