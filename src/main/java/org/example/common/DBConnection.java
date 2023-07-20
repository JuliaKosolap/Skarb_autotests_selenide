package org.example.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String dbUrl = "jdbc:sqlserver://185.149.40.46:1433;database=SkarbNgoDB;encrypt=false";
    private static String userName = "sa";
    private static String password = "37Y5Nb8uTo2";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } return connection;

    }
}
