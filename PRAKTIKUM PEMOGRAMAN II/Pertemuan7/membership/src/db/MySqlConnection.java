package Pertemuan7.membership.src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_membership";
    private final static String DB_USER = "root@localhost";
    private final static String DB_PASS = "";

    private static MySqlConnection instance;

    // Private constructor to enforce Singleton pattern
    private MySqlConnection() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Make sure it's in the classpath.");
            e.printStackTrace();
        }
    }

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Successfully connected to the database.");
        } catch (SQLException e) {
            System.out.println("Connection to database failed! Check DB URL, user, and password.");
            e.printStackTrace();
        }
        return connection;
    }
}
