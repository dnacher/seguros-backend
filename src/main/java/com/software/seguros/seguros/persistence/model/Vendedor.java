package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "vendedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Vendedor extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;

    @Column(name = "activo")
    private Boolean activo;

    public String getNombreYApellido() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido;
    }

    public String toStringLog() {
        return "Vendedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", departamento='" + departamento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", activo=" + activo +
                '}';
    }
}

