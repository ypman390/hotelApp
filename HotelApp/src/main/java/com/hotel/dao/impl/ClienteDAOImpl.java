package com.hotel.dao.impl;

import com.hotel.hotelapp.dao.ClienteDAO;
import com.hotel.model.Cliente;
import com.hotel.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente findById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        Cliente cliente = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public boolean insert(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, email, telefono, dni, " +
                "fecha_nacimiento, saldo_puntos, num_estancias, activo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDni());
            ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            ps.setBigDecimal(7, cliente.getSaldoPuntos());
            ps.setInt(8, cliente.getNumEstancias());
            ps.setBoolean(9, cliente.isActivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, apellidos=?, email=?, telefono=?, dni=?, " +
                "fecha_nacimiento=?, saldo_puntos=?, num_estancias=?, activo=? WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDni());
            ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            ps.setBigDecimal(7, cliente.getSaldoPuntos());
            ps.setInt(8, cliente.getNumEstancias());
            ps.setBoolean(9, cliente.isActivo());
            ps.setInt(10, cliente.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método auxiliar para mapear ResultSet a Cliente
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDni(rs.getString("dni"));
        cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        cliente.setSaldoPuntos(rs.getBigDecimal("saldo_puntos"));
        cliente.setNumEstancias(rs.getInt("num_estancias"));
        cliente.setActivo(rs.getBoolean("activo"));
        return cliente;
    }
}