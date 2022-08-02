package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "siniestro")
public class Siniestro {

    public Siniestro(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(name = "numero_siniestro")
    private String numeroSiniestro;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "poliza")
    private Poliza poliza;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "es_deducible")
    private Boolean esDeducible;

    @Column(name = "importe_deducible")
    private Integer importeDeducible;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadoSiniestro estadoSiniestro;

    @Column(name = "informacion")
    private String informacion;

    @Version
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroSiniestro() {
        return numeroSiniestro;
    }

    public void setNumeroSiniestro(String description) {
        this.numeroSiniestro = description;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEsDeducible() {
        return esDeducible;
    }

    public String getEsDeducibleToString() {
        if(esDeducible){
            return "Si";
        }else{
            return "No";
        }
    }

    public void setEsDeducible(Boolean esDeducible) {
        this.esDeducible = esDeducible;
    }

    public Integer getImporteDeducible() {
        return importeDeducible;
    }

    public void setImporteDeducible(Integer importeDeducible) {
        this.importeDeducible = importeDeducible;
    }

    public EstadoSiniestro getEstadoSiniestro() {
        return estadoSiniestro;
    }

    public void setEstadoSiniestro(EstadoSiniestro estadoSiniestro) {
        this.estadoSiniestro = estadoSiniestro;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getFechaToString() {
        return UtilsGeneral.getFechaFormato(getFecha());
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String toStringLog() {
        return "Siniestro{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", numeroSiniestro='" + numeroSiniestro + '\'' +
                ", poliza=" + poliza +
                ", fecha=" + fecha +
                ", esDeducible=" + esDeducible +
                ", importeDeducible=" + importeDeducible +
                ", estadoSiniestro=" + estadoSiniestro +
                ", informacion='" + informacion + '\'' +
                ", version=" + version +
                '}';
    }
}
