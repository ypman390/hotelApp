package com.hotel.hotelapp.dao;

import com.hotel.model.ServicioExtra;
import java.util.List;

public interface ServicioExtraDAO {

    // Obtener todos los servicios
    List<ServicioExtra> findAll();

    // Obtener servicio por ID
    ServicioExtra findById(int id);

    // Obtener servicios por reserva
    List<ServicioExtra> findByReserva(int idReserva);

    // Insertar nuevo servicio
    boolean insert(ServicioExtra servicioExtra);

    // Actualizar servicio existente
    boolean update(ServicioExtra servicioExtra);

    // Eliminar servicio por ID
    boolean delete(int id);
}