package org.example.test;

import org.example.common.VolunteerDBService;
import org.testng.annotations.Test;
import test_data.RandomData;

import java.sql.SQLException;

public class Task14 {
    @Test
    public void createVolunteerUsingJDBC() throws SQLException {
        VolunteerDBService service = new VolunteerDBService();
//        service.create(
//                "Julia", "Kosolap", "$2a$10$KlmvSoQF2MzM1nHFqA0Y9uHZQ3GHTHbKzlPQTVKGcL6u03npY2Qzm",
//                "MALE", "test1001@gmail.com", "ROLE_VOLUNTEER", "ACTIVE", "380673335577",
//                null, null, null, true, false, null,
//                "manager", null, "2023-07-05 09:19:00.25106",
//                "2023-07-05 09:19:00.25106", "2023-07-05 08:19:00.25106", "UK",
//                false, false, false, false, false,
//                true, "3001", false, 0);
        service.search();
        service.closeConnectionsAndStatements();
    }
}
