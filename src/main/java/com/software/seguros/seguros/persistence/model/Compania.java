package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "compania")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Compania extends AbstractDomainEntity {

    public Compania(String nombre){
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "web")
    private String web;

    @Column(name="numero_auxilio")
    private String numeroAuxilio;

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringLog() {
        return "Compania{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", web='" + web + '\'' +
                ", numeroAuxilio='" + numeroAuxilio + '\'' +
                '}';
    }
}
