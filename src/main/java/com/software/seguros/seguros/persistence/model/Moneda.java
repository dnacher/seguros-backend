package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "moneda")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Moneda extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "simbolo")
    private String simbolo;

    @Override
    public String toString() {
        return simbolo;
    }

    public String toStringLog() {
        return "Moneda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", simbolo='" + simbolo + '\'' +
                '}';
    }
}
