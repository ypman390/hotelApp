package com.hotel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL      = "jdbc:mariadb://localhost:3306/hotel_db";
    private static final String USUARIO  = "ibantareas";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("✅ Driver MariaDB cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error al cargar el driver: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}