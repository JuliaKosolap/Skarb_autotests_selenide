package org.example.common;

import netscape.javascript.JSException;
import org.example.entity.VolunteerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolunteerDBService {
    static final String JDBC_DRIVER = "org.h2.Driver";
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
    public void search() throws SQLException {
        try {
            connection = DBConnection.getConnection();
            String sql = "select * from users";
            createSt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
