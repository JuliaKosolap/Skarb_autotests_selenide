package org.example.common;

import org.example.entity.Volunteer;
import org.example.entity.VolunteerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.common.CustomLogger.logger;

public class VolunteerDBService {
    private  Connection connection;
    private PreparedStatement selectSt;
    private PreparedStatement updateSt;
    private PreparedStatement createSt;
    public VolunteerDBService() {
     try {
        connection = DBConnection.getConnection();
         String createVolunteerSql = "insert into users (first_name, last_name, password, sex, email, role, status, " +
                 "phone, skype, linked_in, social_networks, email_confirmed, owner, organization_id, " +
                 "position_in_organization, volunteer_data_id, last_active_date, created_date, updated_date, " +
                 "locale, phone_displayed, skype_displayed, linked_in_displayed, social_displayed, " +
                 "notifications_to_email, news_to_email, avatar_id, mfa_enabled, completed_task_count) values (?,?,?,?,?," +
                 "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         logger.info("Insert statement was created");
         createSt = connection.prepareStatement(createVolunteerSql);

         String updateStatusSql = "UPDATE users SET email_confirmed = '1' WHERE email = ?";
         logger.info("Update statement was created");
         updateSt = connection.prepareStatement(updateStatusSql);

         String sql = "select * from users";
         logger.info("Select statement was created");
         selectSt = connection.prepareStatement(sql);
    } catch (SQLException e) {
         e.printStackTrace();
     }
    }
    //this method creates new a volunteer in the Users table
    public int createVolunteer(VolunteerDTO volunteer) throws SQLException {
        createSt.setString(1, volunteer.getFirstName());
        createSt.setString(2, volunteer.getLastName());
        createSt.setString(3, volunteer.getPassword());
        createSt.setString(4, volunteer.getSex());
        createSt.setString(5, volunteer.getEmail());
        createSt.setString(6, volunteer.getRole());
        createSt.setString(7, volunteer.getStatus());
        createSt.setString(8, volunteer.getPhoneNumber());
        createSt.setString(9, volunteer.getSkype());
        createSt.setString(10, volunteer.getLinkedIn());
        createSt.setString(11, volunteer.getSocialNetworks());
        createSt.setBoolean(12, volunteer.isEmailConfirmed());
        createSt.setBoolean(13, volunteer.isOwner());
        createSt.setString(14, volunteer.getOrganizationId());
        createSt.setString(15, volunteer.getPositionInOrganization());
        createSt.setString(16, volunteer.getVolunteerDataId());
        createSt.setString(17, volunteer.getLastActiveDate());
        createSt.setString(18, volunteer.getCreatedDate());
        createSt.setString(19, volunteer.getUpdatedDate());
        createSt.setString(20, volunteer.getLocale());
        createSt.setBoolean(21, volunteer.isPhoneDisplayed());
        createSt.setBoolean(22, volunteer.isSkypeDisplayed());
        createSt.setBoolean(23, volunteer.isLinkedInDisplayed());
        createSt.setBoolean(24, volunteer.isSocialDisplayed());
        createSt.setBoolean(25, volunteer.isNotificationsToEmail());
        createSt.setBoolean(26, volunteer.isNewsToEmail());
        createSt.setString(27, volunteer.getAvatarId());
        createSt.setBoolean(28, volunteer.isMfaEnabled());
        createSt.setInt(29, volunteer.getCompletedTaskNumber());
        logger.info("Update was executed");
        return createSt.executeUpdate();
    }

    //This method updates email_confirmed field from 0 to 1 in order to make new volunteer account active without
    //login to MailHog
    public int updateEmailConfirmedField(Volunteer volunteer) throws SQLException {
        updateSt.setString(1, volunteer.getEmail());
        return updateSt.executeUpdate();
    }
    public void selectFromUsersTable() throws SQLException {
        ResultSet resultSet = selectSt.executeQuery();
        System.out.println(resultSet.findColumn("first_name"));
    }
    public void closeConnectionsAndStatements() {
        try {
            connection.close();
            selectSt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
