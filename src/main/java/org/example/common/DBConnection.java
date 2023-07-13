package org.example.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String dbUrl = "jdbc:h2:mem:SkarbNgoDB;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false";
    private static String userName = "user";
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
