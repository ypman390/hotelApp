package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {

    private int id;
    private int idCliente;
    private int idHabitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private BigDecimal precioTotal;
    private int numPersonas;
    private String observaciones;
    private boolean confirmada;

    // Constructor vacío
    public Reserva() {}

    // Constructor completo
    public Reserva(int id, int idCliente, int idHabitacion, LocalDate fechaEntrada,
                   LocalDate fechaSalida, BigDecimal precioTotal, int numPersonas,
                   String observaciones, boolean confirmada) {
        this.id = id;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioTotal = precioTotal;
        this.numPersonas = numPersonas;
        this.observaciones = observaciones;
        this.confirmada = confirmada;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }

    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }

    public int getNumPersonas() { return numPersonas; }
    public void setNumPersonas(int numPersonas) { this.numPersonas = numPersonas; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    @Override
    public String toString() {
        return "Reserva{id=" + id + ", idCliente=" + idCliente + ", idHabitacion=" + idHabitacion +
                ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + "}";
    }
}