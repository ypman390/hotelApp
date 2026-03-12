package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicioExtra {

    private int id;
    private int idReserva;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;
    private LocalDate fechaServicio;
    private boolean incluido;

    // Constructor vacío
    public ServicioExtra() {}

    // Constructor completo
    public ServicioExtra(int id, int idReserva, String nombre, String descripcion,
                         BigDecimal precio, int cantidad,
                         LocalDate fechaServicio, boolean incluido) {
        this.id = id;
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaServicio = fechaServicio;
        this.incluido = incluido;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDate getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(LocalDate fechaServicio) { this.fechaServicio = fechaServicio; }

    public boolean isIncluido() { return incluido; }
    public void setIncluido(boolean incluido) { this.incluido = incluido; }

    @Override
    public String toString() {
        return "ServicioExtra{id=" + id + ", idReserva=" + idReserva +
                ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}
