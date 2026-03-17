package com.hotel.hotelapp.dao;

import com.hotel.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    // Obtener todos los clientes
    List<Cliente> findAll();

    // Obtener cliente por ID
    Cliente findById(int id);

    // Obtener cliente por email
    Cliente findByEmail(String email);

    // Insertar nuevo cliente
    boolean insert(Cliente cliente);

    // Actualizar cliente existente
    boolean update(Cliente cliente);

    // Eliminar cliente por ID
    boolean delete(int id);
}