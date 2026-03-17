package com.hotel.hotelapp.dao;

import com.hotel.model.Reserva;
import java.util.List;

public interface ReservaDAO {

    // Obtener todas las reservas
    List<Reserva> findAll();

    // Obtener reserva por ID
    Reserva findById(int id);

    // Obtener reservas por cliente
    List<Reserva> findByCliente(int idCliente);

    // Obtener reservas por habitacion
    List<Reserva> findByHabitacion(int idHabitacion);

    // Insertar nueva reserva
    boolean insert(Reserva reserva);

    // Actualizar reserva existente
    boolean update(Reserva reserva);

    // Eliminar reserva por ID
    boolean delete(int id);

    // Confirmar reserva
    boolean confirmar(int id);
}