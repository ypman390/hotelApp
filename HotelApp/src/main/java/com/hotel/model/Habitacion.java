package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Habitacion {

    private int id;
    private int numero;
    private String tipo;
    private String descripcion;
    private BigDecimal precioNoche;
    private int capacidad;
    private LocalDate fechaUltimaLimpieza;
    private boolean disponible;

    // Constructor vacío
    public Habitacion() {}

    // Constructor completo
    public Habitacion(int id, int numero, String tipo, String descripcion,
                      BigDecimal precioNoche, int capacidad,
                      LocalDate fechaUltimaLimpieza, boolean disponible) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioNoche = precioNoche;
        this.capacidad = capacidad;
        this.fechaUltimaLimpieza = fechaUltimaLimpieza;
        this.disponible = disponible;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(BigDecimal precioNoche) { this.precioNoche = precioNoche; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public LocalDate getFechaUltimaLimpieza() { return fechaUltimaLimpieza; }
    public void setFechaUltimaLimpieza(LocalDate fechaUltimaLimpieza) { this.fechaUltimaLimpieza = fechaUltimaLimpieza; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Habitacion{id=" + id + ", numero=" + numero + ", tipo='" + tipo + "', precioNoche=" + precioNoche + "}";
    }
}