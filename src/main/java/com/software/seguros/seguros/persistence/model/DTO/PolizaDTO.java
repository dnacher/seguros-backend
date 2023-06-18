package com.software.seguros.seguros.persistence.model.DTO;

import com.software.seguros.seguros.persistence.model.Cliente;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import com.software.seguros.seguros.persistence.model.Vendedor;

/**
 * Daniel Nacher
 * 2023-06-17
 */
public class PolizaDTO implements PolizaDTOInt{

    private Producto producto;
    private TipoProducto tipoProducto;
    private Cliente cliente;
    private Compania compania;
    private Vendedor vendedor;
    private Double total;
    private String moneda;
    private String cerradoPor;
    private Double comisionValor;

    public PolizaDTO(){}

    public PolizaDTO(Double total, Producto producto, TipoProducto tipoProducto, Compania compania, Cliente cliente, Vendedor vendedor) {
        this.total = total;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.compania = compania;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public PolizaDTO(Double total, TipoProducto tipoProducto) {
        this.total = total;
        this.tipoProducto = tipoProducto;
    }

    public PolizaDTO(Producto producto, TipoProducto tipoProducto, Cliente cliente, Compania compania,
                     Vendedor vendedor, Double total, String moneda, String cerradoPor, Double comisionValor) {
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.cliente = cliente;
        this.compania = compania;
        this.vendedor = vendedor;
        this.total = total;
        this.moneda = moneda;
        this.cerradoPor = cerradoPor;
        this.comisionValor = comisionValor;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCerradoPor() {
        return cerradoPor;
    }

    public void setCerradoPor(String cerradoPor) {
        this.cerradoPor = cerradoPor;
    }

    public Double getComisionValor() {
        return comisionValor;
    }

    public void setComisionValor(Double comisionValor) {
        this.comisionValor = comisionValor;
    }
}
