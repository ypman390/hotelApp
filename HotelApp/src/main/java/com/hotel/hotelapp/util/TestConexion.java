package com.hotel.hotelapp.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {

    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");

        try (Connection con = ConexionDB.getConnection()) {
            if (con != null) {
                System.out.println("✅ Conexión exitosa a hotel_db");
                System.out.println("Base de datos: " + con.getCatalog());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}