package org.example.pages.registration;

import io.qameta.allure.Step;
import org.example.entity.Gender;
import org.example.entity.Organization;
import org.example.pages.BasePage;
import org.example.pages.NavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.common.CustomLogger.logger;

public class OrganizationRegistrationPage extends NavigationMenu {
    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "male")
    private WebElement maleRadioButton;
    @FindBy(id = "female")
    private WebElement femaleRadioButton;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "organizationName")
    private WebElement organization;

    @FindBy(id = "positionInOrganization")
    private WebElement positionInOrganization;

    @FindBy(name = "submit")
    private WebElement submitButton;

    public boolean isInitialized() {
        return email.isDisplayed();
    }

    public OrganizationRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationRegistrationPage enterEmail(String email) {
        logger.info("Email was typed");
        this.email.sendKeys(email);
        return this;
    }
    public OrganizationRegistrationPage enterFirstName(String firstName) {
        logger.info("First name was typed");
        this.firstName.sendKeys(firstName);
        return this;
    }

    public OrganizationRegistrationPage enterLastName(String lastName) {
        logger.info("Last name was typed");
        this.lastName.sendKeys(lastName);
        return this;
    }

    public OrganizationRegistrationPage selectRadioButton(Gender gender) {
        logger.info("Gender is selected");
        switch (gender) {
            case MALE -> maleRadioButton.click();
            case FEMALE -> femaleRadioButton.click();
        }
        return this;
    }

    public OrganizationRegistrationPage enterPassword(String password) {
        logger.info("Password was typed");
        this.password.sendKeys(password);
        return this;
    }

    public OrganizationRegistrationPage enterConfirmPassword(String confirmPassword) {
        logger.info("Password confirmation was typed");
        this.confirmPassword.sendKeys(confirmPassword);
        return this;
    }

    public OrganizationRegistrationPage enterOrganization(String organization) {
        logger.info("Organization was typed");
        this.organization.sendKeys(organization);
        return this;
    }

    public OrganizationRegistrationPage enterPositionInOrganization(String position) {
        logger.info("Position in organization was typed");
        this.positionInOrganization.sendKeys(position);
        return this;
    }
    @Step("Click Submit button")

    //This method submits the partner creation form and returns success page in case of success registration or current page is case of any error
    public BasePage submit(){
        logger.info("Submit button was clicked");
        submitButton.click();

        logger.info("A title of the current page is got");
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Organization registration") || pageTitle.equals("Регистрация организации") || pageTitle.equals("Реєстрація організацї")) {
            return this;
        } else {
            return new SuccessRegistrationPage(driver);
        }
    }
    @Step("Fill all mandatory fields")
    //This method fills the mandatory fields for registration with provided values
    public OrganizationRegistrationPage fillInMandatoryFields(Organization organization) {
        enterFirstName(organization.getFirstName());
        enterLastName(organization.getLastName());
        enterEmail(organization.getEmail());
        selectRadioButton(organization.getGender());
        enterPassword(organization.getPassword());
        enterConfirmPassword(organization.getConfirmPassword());
        enterOrganization(organization.getOrganizationName());
        enterPositionInOrganization(organization.getOrganizationPosition());
        return this;
    }
}
