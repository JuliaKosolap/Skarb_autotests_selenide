package org.example.test;

import com.codeborne.selenide.SelenideElement;
import org.example.common.CustomListener;
import org.example.entity.Volunteer;
import org.example.pages.HomePage;
import org.example.pages.registration.RegistrationPage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.pages.registration.VolunteerCreationPage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exactText;
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
        Volunteer volunteer = new Volunteer(firstName, lastName, email, phoneNumber, password, confirmPassword);

        logger.info("Home page was opened");
        HomePage homePage = new HomePage();

        logger.info("Registration page was opened");
        RegistrationPage registrationPage = homePage.goToRegistrationPage();

        logger.info("Volunteer creation page was opened");
        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage();

        logger.info("All mandatory fields were filled");
        volunteerCreationPage.fillInMandatoryFields(volunteer);

        logger.info("Submit button was clicked");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) volunteerCreationPage.submit();

        successPage.getSuccessMessage().
                shouldHave(exactText("Congratulation! Your registration succeeded! Message was sent to your email. Please confirm it."));

    }

    @Test
    public void createVolunteerWithInvalidEmail() {
        logger.info("Data for new volunteer was generated with invalid email");
        Volunteer volunteerWithInvalidEmail = new Volunteer(firstName, lastName, invalidEmail, phoneNumber, password, confirmPassword);

        logger.info("Home page was opened");
        HomePage homePage = new HomePage();

        logger.info("Registration page was opened");
        RegistrationPage registrationPage = homePage.goToRegistrationPage();

        logger.info("Volunteer creation page was opened");
        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage();

        logger.info("All mandatory fields were filled");
        volunteerCreationPage.fillInMandatoryFields(volunteerWithInvalidEmail);

        logger.info("Submit button was clicked");
        volunteerCreationPage.submit();

        volunteerCreationPage.getEmailError().shouldHave(exactText("Email is incorrect"));
    }

    @Test
    public void createVolunteerWithEmptyFields() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();

        logger.info("Registration page was opened");
        RegistrationPage registrationPage = homePage.goToRegistrationPage();

        logger.info("Volunteer creation page was opened");
        registrationPage.goToVolunteerCreationPage();
        VolunteerCreationPage volunteerCreationPage = new VolunteerCreationPage();

        logger.info("Submit button was clicked without filling the mandatory fields");
        volunteerCreationPage.submit();

        logger.info("All error messages on the page were collected");
        ArrayList<SelenideElement> allErrorsOnPage = volunteerCreationPage.getAllErrorsOnPage();


       Assert.assertEquals(allErrorsOnPage.size(), 5);
       allErrorsOnPage.iterator().next().shouldHave(exactText("Field can`t be empty"));
    }
}
