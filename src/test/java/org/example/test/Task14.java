package org.example.test;

import org.example.crudservice.VolunteerCrudService;
import org.example.entity.Volunteer;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Task14 {
    @Test
    public void createVolunteerUsingJDBC() throws SQLException {
        Volunteer volunteer = new Volunteer("Julia", "Kosolap", "$2a$10$KlmvSoQF2MzM1nHFqA0Y9uHZQ3GHTHbKzlPQTVKGcL6u03npY2Qzm",
                "MALE", "test1002@gmail.com", "ROLE_VOLUNTEER", "ACTIVE", "380673335577",
                null, null, null, true, false, null,
                "manager", null, "2023-07-05 09:19:00.25106",
                "2023-07-05 09:19:00.25106", "2023-07-05 08:19:00.25106", "UK",
                false, false, false, false, false,
                true, "3001", false, 0);
        VolunteerCrudService service = new VolunteerCrudService();
        service.create(volunteer);
        System.out.println(service.getAllUsersEmails());
    }
}
