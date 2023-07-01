package org.example.test;

import entity.Volunteer;
import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.example.pages.SuccessRegistrationPage;
import org.example.pages.VolunteerCreationPage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import test_data.RandomData;

import java.util.List;

import static common.CustomLogger.logger;

@Listeners(common.CustomListener.class)
public class Task5_1 extends BaseTest {

    private String firstName = RandomData.randomFirstOrLastName(8);
    private String lastName = RandomData.randomFirstOrLastName(8);
    private String email = RandomData.randomEmail();
    private String phoneNumber = RandomData.randomPhoneNumber();
    private String password = RandomData.randomPassword(8);
    private String confirmPassword = password;
    private String invalidEmail = RandomData.invalidRandomEmail();


    @Test
    public void createVolunteerWithValidValues() {
        Volunteer volunteer = new Volunteer(firstName, lastName, email, phoneNumber, password, confirmPassword);
        logger.info("Data for new volunteer was generated");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        logger.info("Registration page was opened");

        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage(driver);
        Assert.assertTrue(volunteerCreationPage.isInitialized());
        logger.info("Volunteer creation page was opened");

        volunteerCreationPage.fillInMandatoryFields(volunteer);
        logger.info("All mandatory fields were filled");

        SuccessRegistrationPage successPage = (SuccessRegistrationPage) volunteerCreationPage.submit();
        logger.info("Submit button was clicked");

        Assert.assertTrue(successPage.isInitialized());
        Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");
    }

    @Test
    public void createVolunteerWithInvalidEmail() {
        Volunteer volunteerWithInvalidEmail = new Volunteer(firstName, lastName, invalidEmail, phoneNumber, password, confirmPassword);
        logger.info("Data for new volunteer was generated with invalid email");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        logger.info("Registration page was opened");

        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage(driver);
        Assert.assertTrue(volunteerCreationPage.isInitialized());
        logger.info("Volunteer creation page was opened");

        volunteerCreationPage.fillInMandatoryFields(volunteerWithInvalidEmail);
        logger.info("All mandatory fields were filled");

        volunteerCreationPage.submit();
        logger.info("Submit button was clicked");

        Assert.assertEquals(volunteerCreationPage.getEmailError(), "Email is incorrect");
    }

    @Test
    public void createVolunteerWithEmptyFields() {
        VolunteerCreationPage volunteerCreationPage = (VolunteerCreationPage) (new HomePage(driver))
                .goToRegistrationPage().
                goToVolunteerCreationPage().
                submit();
        logger.info("Home page was opened");
        logger.info("Registration page was opened");
        logger.info("Volunteer creation page was opened");
        logger.info("Submit button was clicked without filling the mandatory fields");

        List<String> allErrorsOnPage = volunteerCreationPage.getAllErrorsOnPage();
        logger.info("All error messages on the page were collected");
        
        Assert.assertEquals(allErrorsOnPage.size(), 5);
        Assert.assertTrue(volunteerCreationPage.checkEmptyFieldsErrors(allErrorsOnPage));
    }
}
