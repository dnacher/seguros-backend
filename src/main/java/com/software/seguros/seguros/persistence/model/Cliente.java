package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    public Cliente(){}

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

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;

    @Column(name = "cedula_identidad")
    private String cedulaIdentidad;

    @Column(name = "libreta_propiedad")
    private String libretaPropiedad;

    @Transient
    private List<String> tipoProductosCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recomendado_por")
    private Cliente recomendadoPor;

    @Column(name = "fecha_comienzo")
    private Date fechaComienzo;

    @Column(name = "rut")
    private String rut;

    @Column(name = "observaciones")
    private String observaciones;

//    @Version
//    private Long version;

    @Column(name= "activo")
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

    public String getFechaNacimientoToString() {
        return UtilsGeneral.getFechaFormato(fechaNacimiento);
    }

    public void setFechaNacimiento(Date dateBirth) {
        this.fechaNacimiento = dateBirth;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String phone) {
        this.telefono = phone;
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

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String numId) {
        this.cedulaIdentidad = numId;
    }

    public String getLibretaPropiedad() {
        return libretaPropiedad;
    }

    public void setLibretaPropiedad(String propertyId) {
        this.libretaPropiedad = propertyId;
    }

    public List<String> getTipoProductosCliente() {
        return tipoProductosCliente;
    }

    public void setTipoProductosCliente(List<String> tipoProductosCliente) {
        this.tipoProductosCliente = tipoProductosCliente;
    }

    public Cliente getRecomendadoPor() {
        return recomendadoPor;
    }

    public void setRecomendadoPor(Cliente recomendadoPor) {
        this.recomendadoPor = recomendadoPor;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public String getFechaComienzoToString() {
        return UtilsGeneral.getFechaFormato(fechaComienzo);
    }

    public void setFechaComienzo(Date startDate) {
        this.fechaComienzo = startDate;
    }

    public String getFechaNacimienToString() {
        return UtilsGeneral.getFechaFormato(fechaNacimiento);
    }

    public String getNombreYApellido(){
        return  nombre + " " + apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaToString() {
        return UtilsGeneral.getFechaFormato(getFechaComienzo());
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getActivoToString(){
        if(activo!=null){
            if(activo){
                return "activo";
            }else{
                return "inactivo";
            }
        }else{
            return "";
        }
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido;
    }

    public String toStringLog() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", departamento='" + departamento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefono='" + telefono + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", cedulaIdentidad='" + cedulaIdentidad + '\'' +
                ", libretaPropiedad='" + libretaPropiedad + '\'' +
                ", tipoProductosCliente=" + tipoProductosCliente +
                ", recomendadoPor=" + recomendadoPor +
                ", fechaComienzo=" + fechaComienzo +
                ", rut='" + rut + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", activo=" + activo +
                '}';
    }
}

