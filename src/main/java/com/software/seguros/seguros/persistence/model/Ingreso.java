package com.software.seguros.seguros.persistence.model;

import com.software.seguros.seguros.utils.UtilsGeneral;

import javax.persistence.*;

@Entity
@Table(name = "ingreso")
public class Ingreso {

    public Ingreso(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "anio")
    private Integer anio;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "banco")
    private Banco banco;

    @Column(name = "valor")
    private Integer valor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer month) {
        this.mes = month;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer year) {
        this.anio = year;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getMesString(){
        return UtilsGeneral.getMesString(mes);
    }

    public String toStringLog() {
        return "Ingreso{" +
                "id=" + id +
                ", mes=" + mes +
                ", anio=" + anio +
                ", banco=" + banco +
                ", valor=" + valor +
                '}';
    }
}
