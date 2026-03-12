package com.hotel.hotelapp.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Cliente {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private LocalDate fechaNacimiento;
    private BigDecimal saldoPuntos;
    private int numEstancias;
    private boolean activo;

    // Constructor vacío
    public Cliente() {}

    // Constructor completo
    public Cliente(int id, String nombre, String apellidos, String email,
                   String telefono, String dni, LocalDate fechaNacimiento,
                   BigDecimal saldoPuntos, int numEstancias, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.saldoPuntos = saldoPuntos;
        this.numEstancias = numEstancias;
        this.activo = activo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public BigDecimal getSaldoPuntos() { return saldoPuntos; }
    public void setSaldoPuntos(BigDecimal saldoPuntos) { this.saldoPuntos = saldoPuntos; }

    public int getNumEstancias() { return numEstancias; }
    public void setNumEstancias(int numEstancias) { this.numEstancias = numEstancias; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', apellidos='" + apellidos + "', email='" + email + "'}";
    }
}