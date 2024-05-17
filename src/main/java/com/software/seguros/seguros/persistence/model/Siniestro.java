package com.software.seguros.seguros.persistence.model;

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
@Table(name = "siniestro")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Siniestro extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(name = "numero_siniestro")
    private String numeroSiniestro;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "poliza")
    private Poliza poliza;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "es_deducible")
    private Boolean esDeducible;

    @Column(name = "importe_deducible")
    private Integer importeDeducible;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadoSiniestro estadoSiniestro;

    @Column(name = "informacion")
    private String informacion;

    public String getEsDeducibleToString() {
        if(esDeducible){
            return "Si";
        }else{
            return "No";
        }
    }

    public String toStringLog() {
        return "Siniestro{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", numeroSiniestro='" + numeroSiniestro + '\'' +
                ", poliza=" + poliza +
                ", fecha=" + fecha +
                ", esDeducible=" + esDeducible +
                ", importeDeducible=" + importeDeducible +
                ", estadoSiniestro=" + estadoSiniestro +
                ", informacion='" + informacion + '\'' +
                '}';
    }
}
