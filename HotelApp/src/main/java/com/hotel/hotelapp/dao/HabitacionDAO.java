package com.hotel.hotelapp.dao;

import com.hotel.model.Habitacion;
import java.util.List;

public interface HabitacionDAO {

    // Obtener todas las habitaciones
    List<Habitacion> findAll();

    // Obtener habitación por ID
    Habitacion findById(int id);

    // Obtener habitaciones disponibles
    List<Habitacion> findDisponibles();

    // Obtener habitaciones por tipo
    List<Habitacion> findByTipo(String tipo);

    // Insertar nueva habitación
    boolean insert(Habitacion habitacion);

    // Actualizar habitación existente
    boolean update(Habitacion habitacion);

    // Eliminar habitación por ID
    boolean delete(int id);
}