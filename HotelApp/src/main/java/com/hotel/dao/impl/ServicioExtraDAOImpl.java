package com.hotel.dao.impl;

import com.hotel.hotelapp.dao.ServicioExtraDAO;
import com.hotel.model.ServicioExtra;
import com.hotel.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioExtraDAOImpl implements ServicioExtraDAO {

    @Override
    public List<ServicioExtra> findAll() {
        List<ServicioExtra> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios_extra";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                servicios.add(mapearServicio(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    @Override
    public ServicioExtra findById(int id) {
        String sql = "SELECT * FROM servicios_extra WHERE id = ?";
        ServicioExtra servicio = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = mapearServicio(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }

    @Override
    public List<ServicioExtra> findByReserva(int idReserva) {
        List<ServicioExtra> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios_extra WHERE id_reserva = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    servicios.add(mapearServicio(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    @Override
    public boolean insert(ServicioExtra servicio) {
        String sql = "INSERT INTO servicios_extra (id_reserva, nombre, descripcion, precio, " +
                "cantidad, fecha_servicio, incluido) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, servicio.getIdReserva());
            ps.setString(2, servicio.getNombre());
            ps.setString(3, servicio.getDescripcion());
            ps.setBigDecimal(4, servicio.getPrecio());
            ps.setInt(5, servicio.getCantidad());
            ps.setDate(6, Date.valueOf(servicio.getFechaServicio()));
            ps.setBoolean(7, servicio.isIncluido());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ServicioExtra servicio) {
        String sql = "UPDATE servicios_extra SET id_reserva=?, nombre=?, descripcion=?, precio=?, " +
                "cantidad=?, fecha_servicio=?, incluido=? WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, servicio.getIdReserva());
            ps.setString(2, servicio.getNombre());
            ps.setString(3, servicio.getDescripcion());
            ps.setBigDecimal(4, servicio.getPrecio());
            ps.setInt(5, servicio.getCantidad());
            ps.setDate(6, Date.valueOf(servicio.getFechaServicio()));
            ps.setBoolean(7, servicio.isIncluido());
            ps.setInt(8, servicio.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM servicios_extra WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ServicioExtra mapearServicio(ResultSet rs) throws SQLException {
        ServicioExtra servicio = new ServicioExtra();
        servicio.setId(rs.getInt("id"));
        servicio.setIdReserva(rs.getInt("id_reserva"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setPrecio(rs.getBigDecimal("precio"));
        servicio.setCantidad(rs.getInt("cantidad"));
        servicio.setFechaServicio(rs.getDate("fecha_servicio").toLocalDate());
        servicio.setIncluido(rs.getBoolean("incluido"));
        return servicio;
    }
}