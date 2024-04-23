package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cuotas_poliza")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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

    public String toStringLog() {
        return "CuotasPoliza{" +
                "id=" + id +
                ", poliza=" + poliza +
                ", numeroCuota=" + numeroCuota +
                ", fechaRegistrado=" + fechaRegistrado +
                '}';
    }
}
