package com.software.seguros.seguros.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "poliza")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Poliza extends AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "compania")
    private Compania compania;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(name = "numero_poliza")
    private String numeroPoliza;

    @Column(name = "comienzo")
    private LocalDate comienzo;

    @Column(name = "vencimiento")
    private LocalDate vencimiento;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "producto")
    private Producto producto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_producto")
    private TipoProducto tipoProducto;

    @Column(name = "premio")
    private Double premio;

    @Column(name = "prima")
    private Double prima;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "moneda")
    private Moneda moneda;

    @Column(name = "comision_porcentaje")
    private Double comisionPorcentaje;

    @Column(name = "comision_valor")
    private Double comisionValor;

    @Column(name = "comision_vendedor_porcentaje")
    private Double comisionVendedorPorcentaje;

    @Column(name = "comision_vendedor_valor")
    private Double comisionVendedorValor;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "forma_pago")
    private FormaPago formaPago;

    @Column(name = "cuotas")
    private Integer cuotas;

    @Column(name = "comienzo_cuota")
    private LocalDate comienzoCuota;

    @Column(name = "fin_cuota")
    private LocalDate finCuota;

    @Column(name = "importe_cuota")
    private Integer importeCuota;

    @Column(name = "cerrado_por")
    private String cerradoPor;

    @Column(name = "es_app")
    private Boolean esApp;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadoPoliza estado;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "vendedor")
    private CotizacionVendedor vendedor;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "poliza_madre")
    private Poliza polizaMadre;

    @Column(name = "observaciones")
    private String observaciones;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REMOVE , CascadeType.MERGE},orphanRemoval = true,
            fetch = FetchType.EAGER, mappedBy = "poliza")
    private RegistroCuotas registroCuotas;

    public String getEsAppString(){
        if(esApp!=null){
            return esApp ? "Es App" : "No es App";
        }else{
            return "No es App";
        }

    }

    public String getTipoProductoCliente(){
        if(producto==null || tipoProducto==null){
            return "";
        }else {
            return producto.getNombre() + " - " + tipoProducto.getNombre();
        }

    }

    @Override
    public String toString() {
        return numeroPoliza;
    }

    public String toStringLog() {
        return "Poliza{" +
                "id=" + id +
                ", compania=" + compania +
                ", cliente=" + cliente +
                ", numeroPoliza='" + numeroPoliza + '\'' +
                ", comienzo=" + comienzo +
                ", vencimiento=" + vencimiento +
                ", producto=" + producto +
                ", tipoProducto=" + tipoProducto +
                ", premio=" + premio +
                ", prima=" + prima +
                ", moneda=" + moneda +
                ", comisionPorcentaje=" + comisionPorcentaje +
                ", comisionValor=" + comisionValor +
                ", comisionVendedorPorcentaje=" + comisionVendedorPorcentaje +
                ", comisionVendedorValor=" + comisionVendedorValor +
                ", formaPago=" + formaPago +
                ", cuotas=" + cuotas +
                ", comienzoCuota=" + comienzoCuota +
                ", finCuota=" + finCuota +
                ", importeCuota=" + importeCuota +
                ", cerradoPor='" + cerradoPor + '\'' +
                ", esApp=" + esApp +
                ", estado=" + estado +
                ", vendedor=" + vendedor +
                ", polizaMadre=" + polizaMadre +
                ", observaciones='" + observaciones + '\'' +
                ", registroCuotas=" + registroCuotas +
                '}';
    }
}
