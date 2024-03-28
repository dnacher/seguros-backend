package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "banco")
public class Banco extends AbstractDomainEntity {

    public Banco(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringLog() {
        return "Banco{" +
                logString() +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
