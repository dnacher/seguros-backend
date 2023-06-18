package com.software.seguros.seguros.persistence.model.DTO;

/**
 * Daniel Nacher
 * 2023-06-17
 */
public class BancoDTO {

    private Integer id;

    private String uuid;

    private String nombre;

    private String descripcion;

    public BancoDTO(){}

    public BancoDTO(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
