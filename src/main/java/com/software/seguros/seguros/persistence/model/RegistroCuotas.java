package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registro_cuotas")
public class RegistroCuotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "poliza")
    private Poliza poliza;

    @Column(name = "numero_cuotas_pagas")
    private Integer numeroCuotasPagas;

    @Column(name = "ultima_fecha_actualizacion")
    private Date ultimaFechaActualizacion;

    public RegistroCuotas(){}

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

    public Integer getNumeroCuotasPagas() {
        return numeroCuotasPagas;
    }

    public void setNumeroCuotasPagas(Integer numeroCuotasPagas) {
        this.numeroCuotasPagas = numeroCuotasPagas;
    }

    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public String getNumeroPoliza(){
        return poliza.getNumeroPoliza();
    }

    public String getCuotas(){
        return poliza.getCuotas().toString();
    }

    public String toStringLog() {
        return "RegistroCuotas{" +
                "id=" + id +
                ", poliza=" + poliza +
                ", numeroCuotasPagas=" + numeroCuotasPagas +
                ", ultimaFechaActualizacion=" + ultimaFechaActualizacion +
                '}';
    }
}
