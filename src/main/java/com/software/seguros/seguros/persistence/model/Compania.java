package com.software.seguros.seguros.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "compania")
public class Compania extends AbstractDomainEntity {

    public Compania(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String phone) {
        this.telefono = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getNumeroAuxilio() {
        return numeroAuxilio;
    }

    public void setNumeroAuxilio(String numeroAuxilio) {
        this.numeroAuxilio = numeroAuxilio;
    }

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
