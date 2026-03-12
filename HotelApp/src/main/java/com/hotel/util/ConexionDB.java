package com.hotel.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL      = "jdbc:mariadb://localhost:3306/hotel_db";
    private static final String USUARIO  = "ibantareas";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}