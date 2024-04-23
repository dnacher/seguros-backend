package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Cliente extends AbstractDomainEntity {

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
    private LocalDate fechaNacimiento;

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
    private LocalDate fechaComienzo;

    @Column(name = "rut")
    private String rut;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name= "activo")
    private Boolean activo;


    public String getFechaNacimientoToString() {
        return UtilsGeneral.getFechaFormato(fechaNacimiento);
    }

    public String getFechaComienzoToString() {
        return UtilsGeneral.getFechaFormato(fechaComienzo);
    }

    public String getFechaNacimienToString() {
        return UtilsGeneral.getFechaFormato(fechaNacimiento);
    }

    public String getNombreYApellido(){
        return  nombre + " " + apellido;
    }

    public String getFechaToString() {
        return UtilsGeneral.getFechaFormato(getFechaComienzo());
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

