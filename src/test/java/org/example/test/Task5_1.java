package org.example.test;

import org.example.entity.Volunteer_old;
import org.example.common.CustomListener;
import org.example.pages.HomePage;
import org.example.pages.registration.RegistrationPage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.pages.registration.VolunteerCreationPage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import test_data.RandomData;

import java.util.List;

import static org.example.common.CustomLogger.logger;

@Listeners(CustomListener.class)
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
        logger.info("Data for new volunteer was generated");
        Volunteer_old volunteer = new Volunteer_old(firstName, lastName, email, phoneNumber, password, confirmPassword);

        logger.info("Home page was opened");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        logger.info("Registration page was opened");
        RegistrationPage registrationPage = homePage.goToRegistrationPage();

        logger.info("Volunteer creation page was opened");
        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage(driver);
        Assert.assertTrue(volunteerCreationPage.isInitialized());

        logger.info("All mandatory fields were filled");
        volunteerCreationPage.fillInMandatoryFields(volunteer);

        logger.info("Submit button was clicked");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) volunteerCreationPage.submit();

        Assert.assertTrue(successPage.isInitialized());
        Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");
    }

    @Test
    public void createVolunteerWithInvalidEmail() {
        logger.info("Data for new volunteer was generated with invalid email");
        Volunteer_old volunteerWithInvalidEmail = new Volunteer_old(firstName, lastName, invalidEmail, phoneNumber, password, confirmPassword);

        logger.info("Home page was opened");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        logger.info("Registration page was opened");
        RegistrationPage registrationPage = homePage.goToRegistrationPage();

        logger.info("Volunteer creation page was opened");
        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage(driver);
        Assert.assertTrue(volunteerCreationPage.isInitialized());

        logger.info("All mandatory fields were filled");
        volunteerCreationPage.fillInMandatoryFields(volunteerWithInvalidEmail);

        logger.info("Submit button was clicked");
        volunteerCreationPage.submit();

        Assert.assertEquals(volunteerCreationPage.getEmailError(), "Email is incorrect");
    }

    @Test
    public void createVolunteerWithEmptyFields() {
        logger.info("Submit button was clicked without filling the mandatory fields");
        VolunteerCreationPage volunteerCreationPage = (VolunteerCreationPage) (new HomePage(driver))
                .goToRegistrationPage().
                goToVolunteerCreationPage().
                submit();

        logger.info("All error messages on the page were collected");
        List<String> allErrorsOnPage = volunteerCreationPage.getAllErrorsOnPage();

        Assert.assertEquals(allErrorsOnPage.size(), 5);
        Assert.assertTrue(volunteerCreationPage.checkEmptyFieldsErrors(allErrorsOnPage));
    }
}
