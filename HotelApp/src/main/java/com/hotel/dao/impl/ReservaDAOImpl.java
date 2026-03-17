package com.hotel.dao.impl;

import com.hotel.hotelapp.dao.ReservaDAO;
import com.hotel.model.Reserva;
import com.hotel.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    @Override
    public List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                reservas.add(mapearReserva(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public Reserva findById(int id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        Reserva reserva = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    reserva = mapearReserva(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    @Override
    public List<Reserva> findByCliente(int idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE id_cliente = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public List<Reserva> findByHabitacion(int idHabitacion) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE id_habitacion = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHabitacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public boolean insert(Reserva reserva) {
        String sql = "INSERT INTO reservas (id_cliente, id_habitacion, fecha_entrada, " +
                "fecha_salida, precio_total, num_personas, observaciones, confirmada) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdHabitacion());
            ps.setDate(3, Date.valueOf(reserva.getFechaEntrada()));
            ps.setDate(4, Date.valueOf(reserva.getFechaSalida()));
            ps.setBigDecimal(5, reserva.getPrecioTotal());
            ps.setInt(6, reserva.getNumPersonas());
            ps.setString(7, reserva.getObservaciones());
            ps.setBoolean(8, reserva.isConfirmada());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reserva reserva) {
        String sql = "UPDATE reservas SET id_cliente=?, id_habitacion=?, fecha_entrada=?, " +
                "fecha_salida=?, precio_total=?, num_personas=?, observaciones=?, confirmada=? WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdHabitacion());
            ps.setDate(3, Date.valueOf(reserva.getFechaEntrada()));
            ps.setDate(4, Date.valueOf(reserva.getFechaSalida()));
            ps.setBigDecimal(5, reserva.getPrecioTotal());
            ps.setInt(6, reserva.getNumPersonas());
            ps.setString(7, reserva.getObservaciones());
            ps.setBoolean(8, reserva.isConfirmada());
            ps.setInt(9, reserva.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reservas WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean confirmar(int id) {
        String sql = "UPDATE reservas SET confirmada = true WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Reserva mapearReserva(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getInt("id"));
        reserva.setIdCliente(rs.getInt("id_cliente"));
        reserva.setIdHabitacion(rs.getInt("id_habitacion"));
        reserva.setFechaEntrada(rs.getDate("fecha_entrada").toLocalDate());
        reserva.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
        reserva.setPrecioTotal(rs.getBigDecimal("precio_total"));
        reserva.setNumPersonas(rs.getInt("num_personas"));
        reserva.setObservaciones(rs.getString("observaciones"));
        reserva.setConfirmada(rs.getBoolean("confirmada"));
        return reserva;
    }
}