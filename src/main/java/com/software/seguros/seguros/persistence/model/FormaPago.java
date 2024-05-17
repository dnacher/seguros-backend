package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "forma_pago")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FormaPago extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringLog() {
        return "FormaPago{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
