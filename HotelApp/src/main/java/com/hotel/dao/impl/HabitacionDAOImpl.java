package com.hotel.dao.impl;

import com.hotel.hotelapp.dao.HabitacionDAO;
import com.hotel.model.Habitacion;
import com.hotel.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO {

    @Override
    public List<Habitacion> findAll() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                habitaciones.add(mapearHabitacion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    @Override
    public Habitacion findById(int id) {
        String sql = "SELECT * FROM habitaciones WHERE id = ?";
        Habitacion habitacion = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    habitacion = mapearHabitacion(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacion;
    }

    @Override
    public List<Habitacion> findDisponibles() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones WHERE disponible = true";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                habitaciones.add(mapearHabitacion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    @Override
    public List<Habitacion> findByTipo(String tipo) {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones WHERE tipo = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    habitaciones.add(mapearHabitacion(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    @Override
    public boolean insert(Habitacion habitacion) {
        String sql = "INSERT INTO habitaciones (numero, tipo, descripcion, precio_noche, " +
                "capacidad, fecha_ultima_limpieza, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getTipo());
            ps.setString(3, habitacion.getDescripcion());
            ps.setBigDecimal(4, habitacion.getPrecioNoche());
            ps.setInt(5, habitacion.getCapacidad());
            ps.setDate(6, Date.valueOf(habitacion.getFechaUltimaLimpieza()));
            ps.setBoolean(7, habitacion.isDisponible());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Habitacion habitacion) {
        String sql = "UPDATE habitaciones SET numero=?, tipo=?, descripcion=?, precio_noche=?, " +
                "capacidad=?, fecha_ultima_limpieza=?, disponible=? WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getTipo());
            ps.setString(3, habitacion.getDescripcion());
            ps.setBigDecimal(4, habitacion.getPrecioNoche());
            ps.setInt(5, habitacion.getCapacidad());
            ps.setDate(6, Date.valueOf(habitacion.getFechaUltimaLimpieza()));
            ps.setBoolean(7, habitacion.isDisponible());
            ps.setInt(8, habitacion.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM habitaciones WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Habitacion mapearHabitacion(ResultSet rs) throws SQLException {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getInt("id"));
        habitacion.setNumero(rs.getInt("numero"));
        habitacion.setTipo(rs.getString("tipo"));
        habitacion.setDescripcion(rs.getString("descripcion"));
        habitacion.setPrecioNoche(rs.getBigDecimal("precio_noche"));
        habitacion.setCapacidad(rs.getInt("capacidad"));
        habitacion.setFechaUltimaLimpieza(rs.getDate("fecha_ultima_limpieza").toLocalDate());
        habitacion.setDisponible(rs.getBoolean("disponible"));
        return habitacion;
    }
}
