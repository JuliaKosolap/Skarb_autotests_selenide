package org.example.pages.registration;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.entity.Volunteer;
import org.example.pages.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.example.common.CustomLogger.logger;

public class VolunteerCreationPage extends BasePage {
    SelenideElement firstName = $("#firstName");
    SelenideElement lastName = $("#lastName");
    SelenideElement email = $("#email");
    SelenideElement phoneNumber = $("#phoneNumber");
    SelenideElement password = $("#password");
    SelenideElement confirmPassword = $("#confirmPassword");
    SelenideElement submitButton = $(byAttribute("name", "submit"));

    public boolean isInitialized() {
        logger.info("Volunteer creation page is initialized");
        return firstName.isDisplayed();
    }

    public void enterFirstName(String firstName) {
        logger.info("First name is typed");
        this.firstName.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        logger.info("Last name is typed");
        this.lastName.sendKeys(lastName);
    }
    public void enterEmail(String email) {
        logger.info("Email is typed");
        this.email.sendKeys(email);
    }
    public void enterPhoneNumber(String phoneNumber) {
        logger.info("Phone number is typed");
        this.phoneNumber.sendKeys(phoneNumber);
    }
    public void enterPassword(String password) {
        logger.info("Password is typed");
        this.password.sendKeys(password);
    }
    public void enterConfirmPassword(String confirmPassword) {
        logger.info("Password confirmation is typed");
        this.confirmPassword.sendKeys(confirmPassword);
    }
    @Step("Click Submit button")
    public BasePage submit(){

        logger.info("Submit button is clicked");
        submitButton.click();
        String pageTitle = $("title").getText();
        if (pageTitle.equals("Volunteer registration") || pageTitle.equals("Регистрация волонтера") || pageTitle.equals("Реєстрація волонтера")) {
            logger.info("Success registration page was not opened");
            return this;
        } else {
            logger.info("Success registration page was opened");
            return new SuccessRegistrationPage();
        }
    }
    @Step("Fill mandatory fields")
    //This method fills the mandatory fields for registration with provided values
    public VolunteerCreationPage fillInMandatoryFields(Volunteer volunteer) {
        enterFirstName(volunteer.getFirstName());
        enterLastName(volunteer.getLastName());
        enterEmail(volunteer.getEmail());
        enterPhoneNumber(volunteer.getPhoneNumber());
        enterPassword(volunteer.getPassword());
        enterConfirmPassword(volunteer.getConfirmPassword());
        return this;
    }
    public SelenideElement getEmailError() {
        logger.info("Getting email error");
        SelenideElement error = $(byXpath("//div[@name='email']//small[@class='text-danger']"));
        return error;
    }
    public ArrayList<SelenideElement> getAllErrorsOnPage() {
        logger.info("Getting all errors on the page");
        ArrayList<SelenideElement> errors = $$(byAttribute("class", "text-danger")).
                stream().collect(Collectors.toCollection(ArrayList::new));
        return errors;
    }
    public boolean checkEmptyFieldsErrors(List<String> errors) {
        boolean isEqual = false;
        logger.info("Getting errors for empty fields");
        for (String error : errors
        ) {
            if (error.equals("Field can`t be empty"));
            isEqual = true;
        }
        return isEqual;
    }
}
