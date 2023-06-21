package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return firstName.isDisplayed();
    }

    public void enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }
    public void enterEmail(String email) {
        this.email.sendKeys(email);
    }
    public void enterPhoneNumber(String phoneNumber) {
        this.phoneNumber.sendKeys(phoneNumber);
    }
    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }
    public void enterConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
    }
    public BasePage submit(){
        submitButton.click();
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Volunteer registration") || pageTitle.equals("Регистрация волонтера") || pageTitle.equals("Реєстрація волонтера")) {
            return this;
        } else {
            return new SuccessRegistrationPage(driver);
        }
    }
    public String getEmailError() {
        WebElement error = driver.findElement(By.xpath("//div[@name='email']//small[@class='text-danger']"));
        return error.getText();
    }
    public List<String> getAllErrorsOnPage() {
        List<WebElement> errors = driver.findElements(By.className("text-danger"));
        List<String> errorsText = new ArrayList<>();
        for (WebElement error: errors
             ) {
            errorsText.add(error.getText());
        }
        return errorsText;
    }
}
