package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendedor")
public class Vendedor extends AbstractDomainEntity {

    public Vendedor(){}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String surname) {
        this.apellido = surname;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String adress) {
        this.direccion = adress;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String city) {
        this.ciudad = city;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String department) {
        this.departamento = department;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date dateBirth) {
        this.fechaNacimiento = dateBirth;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String cellphone) {
        this.celular = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

