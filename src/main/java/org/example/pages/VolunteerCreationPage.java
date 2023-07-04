package org.example.pages;

import org.example.entity.Volunteer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

import java.util.ArrayList;
import java.util.List;

public class VolunteerCreationPage extends NavigationMenu {
    @FindBy(id="firstName")
    private WebElement firstName;

    @FindBy(id="lastName")
    private WebElement lastName;
    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="confirmPassword")
    private WebElement confirmPassword;

    @FindBy(name = "submit")
    private WebElement submitButton;

    public VolunteerCreationPage(WebDriver driver) {
        super(driver);
    }
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
    public BasePage submit(){

        logger.info("Submit button is clicked");
        submitButton.click();
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Volunteer registration") || pageTitle.equals("Регистрация волонтера") || pageTitle.equals("Реєстрація волонтера")) {
            logger.info("Success registration page was not opened");
            return this;
        } else {
            logger.info("Success registration page was opened");
            return new SuccessRegistrationPage(driver);
        }
    }
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
    public String getEmailError() {
        logger.info("Getting email error");
        WebElement error = driver.findElement(By.xpath("//div[@name='email']//small[@class='text-danger']"));
        return error.getText();
    }
    public List<String> getAllErrorsOnPage() {
        logger.info("Getting all errors on the page");
        List<WebElement> errors = driver.findElements(By.className("text-danger"));
        List<String> errorsText = new ArrayList<>();
        for (WebElement error: errors
             ) {
            errorsText.add(error.getText());
        }
        return errorsText;
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
