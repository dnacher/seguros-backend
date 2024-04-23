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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "registro_cuotas")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RegistroCuotas extends AbstractDomainEntity {

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

    public String getCuotas(){
        if(poliza.getCuotas()!=null){
            return poliza.getCuotas().toString();
        }else {
            return "sin cuotas";
        }
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
