package org.example.test;

import com.aventstack.extentreports.Status;
import org.example.common.CustomListener;
import org.example.common.VolunteerDBService;
import org.example.entity.Volunteer;
import org.example.pages.HomePage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import java.sql.SQLException;

import static org.example.common.CustomListener.test;
import static org.example.common.CustomLogger.logger;
import static org.testng.AssertJUnit.assertTrue;

@Listeners(CustomListener.class)
public class Task15 extends BaseTest {

    @Test(dataProvider = "volunteerData")
    public void registerVolunteer(String firstName, String lastName, String email, String phoneNumber,
                                     String password, String confirmPassword) throws SQLException {

        logger.info("Data for new volunteer is generated");
        Volunteer volunteer = new Volunteer(firstName, lastName, email, phoneNumber, password, confirmPassword);
        test.log(Status.INFO, "Volunteer object was created");

        logger.info("Creating new volunteer...");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage().
                goToRegistrationPage().
                goToVolunteerCreationPage().
                fillInMandatoryFields(volunteer)
                .submit();
        test.log(Status.INFO, "Data were submitted");

       // Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
        //        "Please confirm it.");
        test.log(Status.INFO, "New Volunteer was registered");

        VolunteerDBService volunteerDBService = new VolunteerDBService();
        Assert.assertEquals(volunteerDBService.updateEmailConfirmedField(volunteer), 1);
        test.log(Status.INFO, "New volunteer account was activated via DB");

        logger.info("Trying to login...");
        HomePage homePage = successPage.goToLoginPage().login(volunteer.getEmail(), volunteer.getPassword());
        assertTrue(homePage.isInitialized());
        test.log(Status.INFO, "New volunteer was logged in successfully");
    }
    @DataProvider(name = "volunteerData")
    public Object[][] volunteerDataFeed() {
        Object[][] volunteerData = new Object[1][6];
        int rowCount = 1;
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][0] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][1] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][2] = RandomData.randomEmail();
        }
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][3] = RandomData.randomPhoneNumber();
        }
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][4] = RandomData.randomPassword(8);
        }
        for (int i = 0; i < rowCount; i++) {
            volunteerData[i][5] = volunteerData[i][4];
        }
        return volunteerData;
    }

}
