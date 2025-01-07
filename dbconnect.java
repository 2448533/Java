/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NANDANA BIJU
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {
    private static final String URL = "jdbc:mysql://localhost:3306/conference_db";
    private static final String USER = "root";  // Change with your MySQL user
    private static final String PASSWORD = "Nandana@13";  // Change with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Database connection error", e);
        }
    }
}
