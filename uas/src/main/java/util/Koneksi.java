package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    
    private static final String URL = "jdbc:mysql://localhost:3306/cafe";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connection conn;
    
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi Berhasil!");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}