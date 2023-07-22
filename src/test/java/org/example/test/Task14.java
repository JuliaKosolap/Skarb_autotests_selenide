package org.example.test;

import org.example.common.CustomListener;
import org.example.common.VolunteerDBService;
import org.example.entity.VolunteerDTO;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;
@Listeners(CustomListener.class)
public class Task14 {
    @Test
    public void createVolunteerUsingJDBC() throws SQLException {
        VolunteerDTO volunteer = new VolunteerDTO("John", "Do", "PAssword@1", "FEMALE",
                "test1005@gmail.com", "ROLE_VOLUNTEER", "ACTIVE", "380673335577",
                null, null, null, true, false, null,
                "manager", null, "2023-07-05 09:19:00.25106",
                "2023-07-05 09:19:00.25106", "2023-07-05 08:19:00.25106", "UK",
                false, false, false, false,
                false, true, "1", false, 0);

        VolunteerDBService service = new VolunteerDBService();
        Assert.assertEquals(service.createVolunteer(volunteer), 1);

        service.closeConnectionsAndStatements();
    }
}
