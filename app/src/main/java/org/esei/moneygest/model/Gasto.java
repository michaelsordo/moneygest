package org.esei.moneygest.model;

import java.util.Date;

public class Gasto {

    private int id;
    private String concepto;
    private Double cantidad;
    private Date fecha;
    private String autor;

    public Gasto(int id, String concepto, Double cantidad, Date fecha, String autor) {
        this.id = id;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.autor = autor;
    }

    public Gasto(String concepto, Double cantidad, Date fecha, String autor) {
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.autor = autor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getConcepto() {
        return concepto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getAutor() {
        return autor;
    }
}