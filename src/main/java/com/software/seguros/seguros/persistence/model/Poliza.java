package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "poliza")
public class Poliza {

    public Poliza(){}

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
    private Date comienzo;

    @Column(name = "vencimiento")
    private Date vencimiento;

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
    private Date comienzoCuota;

    @Column(name = "fin_cuota")
    private Date finCuota;

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

    @OneToMany(cascade = {CascadeType.REMOVE , CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "poliza")
    private List<TipoProductoCliente> tipoProductoClienteList;

    @Column(name = "observaciones")
    private String observaciones;

    @OneToOne(cascade = {CascadeType.REMOVE , CascadeType.MERGE},orphanRemoval = true,
            fetch = FetchType.EAGER, mappedBy = "poliza")
    private RegistroCuotas registroCuotas;

//    @Version
//    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public Date getComienzo() {
        return comienzo;
    }

    public String getComienzoToString() {
        return UtilsGeneral.getFechaFormato(comienzo);
    }

    public void setComienzo(Date comienzo) {
        this.comienzo = comienzo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public String getVencimientoToString() {
        return UtilsGeneral.getFechaFormato(vencimiento);
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Double getPremio() {
        return premio;
    }

    public void setPremio(Double premio) {
        this.premio = premio;
    }

    public Double getPrima() {
        return prima;
    }

    public void setPrima(Double prima) {
        this.prima = prima;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Double getComisionPorcentaje() {
        return comisionPorcentaje;
    }

    public void setComisionPorcentaje(Double comision) {
        this.comisionPorcentaje = comision;
    }

    public Double getComisionValor() {
        return comisionValor;
    }

    public void setComisionValor(Double comisionValor) {
        this.comisionValor = comisionValor;
    }

    public Double getComisionVendedorPorcentaje() {
        return comisionVendedorPorcentaje;
    }

    public void setComisionVendedorPorcentaje(Double comisionVendedorPorcentaje) {
        this.comisionVendedorPorcentaje = comisionVendedorPorcentaje;
    }

    public Double getComisionVendedorValor() {
        return comisionVendedorValor;
    }

    public void setComisionVendedorValor(Double comisionVendedorValor) {
        this.comisionVendedorValor = comisionVendedorValor;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Date getComienzoCuota() {
        return comienzoCuota;
    }

    public String getComienzoCuotaToString() {
        return UtilsGeneral.getFechaFormato(comienzoCuota);
    }

    public void setComienzoCuota(Date comienzoCuota) {
        this.comienzoCuota = comienzoCuota;
    }

    public Date getFinCuota() {
        return finCuota;
    }

    public String getFinCuotaToString() {
        return UtilsGeneral.getFechaFormato(finCuota);
    }

    public void setFinCuota(Date finCuota) {
        this.finCuota = finCuota;
    }

    public Integer getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(Integer importeCuota) {
        this.importeCuota = importeCuota;
    }

    public String getCerradoPor() {
        return cerradoPor;
    }

    public void setCerradoPor(String cerradoPor) {
        this.cerradoPor = cerradoPor;
    }

    public Boolean getEsApp() {
        return esApp;
    }

    public void setEsApp(Boolean esApp) {
        this.esApp = esApp;
    }

    public String getEsAppString(){
        if(esApp!=null){
            return esApp ? "Es App" : "No es App";
        }else{
            return "No es App";
        }

    }

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }

    public String getTipoProductoCliente(){
        return producto.getNombre() + " - " + tipoProducto.getNombre();
    }

    public CotizacionVendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(CotizacionVendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Poliza getPolizaMadre() {
        return polizaMadre;
    }

    public void setPolizaMadre(Poliza polizaMadre) {
        this.polizaMadre = polizaMadre;
    }

    public List<TipoProductoCliente> getTipoProductoClienteList() {
        return tipoProductoClienteList;
    }

    public void setTipoProductoClienteList(List<TipoProductoCliente> tipoProductoClienteList) {
        this.tipoProductoClienteList = tipoProductoClienteList;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public RegistroCuotas getRegistroCuotas() {
        return registroCuotas;
    }

    public void setRegistroCuotas(RegistroCuotas registroCuotas) {
        this.registroCuotas = registroCuotas;
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
                ", tipoProductoClienteList=" + tipoProductoClienteList +
                ", observaciones='" + observaciones + '\'' +
                ", registroCuotas=" + registroCuotas +
                '}';
    }
}
