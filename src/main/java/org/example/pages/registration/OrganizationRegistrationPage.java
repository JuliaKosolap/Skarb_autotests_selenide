package org.example.pages.registration;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.entity.Gender;
import org.example.entity.Organization;
import org.example.pages.BasePage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class OrganizationRegistrationPage extends BasePage {
    SelenideElement firstName = $("#firstName");
    SelenideElement lastName = $("#lastName");
    SelenideElement email = $("#email");
    SelenideElement maleRadioButton = $("#male");
    SelenideElement femaleRadioButton = $("#female");
    SelenideElement password = $("#password");
    SelenideElement confirmPassword = $("#confirmPassword");
    SelenideElement organization = $("#organizationName");
    SelenideElement positionInOrganization = $("#positionInOrganization");
    SelenideElement submitButton = $(byAttribute("name", "submit"));

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
        String pageTitle = $("title").getText();
        if (pageTitle.equals("Organization registration") || pageTitle.equals("Регистрация организации") || pageTitle.equals("Реєстрація організацї")) {
            return this;
        } else {
            return new SuccessRegistrationPage();
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
