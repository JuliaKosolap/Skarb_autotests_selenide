package org.example.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolunteerDBService {
    private  Connection connection;
    private PreparedStatement createSt;
    public VolunteerDBService() {
     try {
        connection = DBConnection.getConnection();
         String sql = "select * from users";
         createSt = connection.prepareStatement(sql);
    } catch (SQLException e) {
         e.printStackTrace();
     }
    }
    public void selectFromUsersTable() throws SQLException {
        ResultSet resultSet = createSt.executeQuery();
        System.out.println(resultSet);
    }
    public void closeConnectionsAndStatements() {
        try {
            connection.close();
            createSt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
