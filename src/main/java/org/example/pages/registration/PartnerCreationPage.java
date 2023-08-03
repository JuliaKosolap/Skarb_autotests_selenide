package org.example.pages.registration;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.entity.Gender;
import org.example.entity.Partner;
import org.example.pages.BasePage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class PartnerCreationPage extends BasePage {
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


    public boolean isInitialized() {
        return firstName.isDisplayed();
    }


    public PartnerCreationPage enterFirstName(String firstName) {
        logger.info("First name was typed");
        this.firstName.sendKeys(firstName);
        return this;
    }

    public PartnerCreationPage enterLastName(String lastName) {
        logger.info("Last name was typed");
        this.lastName.sendKeys(lastName);
        return this;
    }

    public PartnerCreationPage enterEmail(String email) {
        logger.info("Email was typed");
        this.email.sendKeys(email);
        return this;
    }

    public PartnerCreationPage selectRadioButton(Gender gender) {
        logger.info("Gender is selected");
        switch (gender) {
            case MALE -> maleRadioButton.click();
            case FEMALE -> femaleRadioButton.click();
        }
        return this;
    }

    public PartnerCreationPage enterPassword(String password) {
        logger.info("Password was typed");
        this.password.sendKeys(password);
        return this;
    }

    public PartnerCreationPage enterConfirmPassword(String confirmPassword) {
        logger.info("Password confirmation was typed");
        this.confirmPassword.sendKeys(confirmPassword);
        return this;
    }

    public PartnerCreationPage enterOrganization(String organization) {
        logger.info("Organization was typed");
        this.organization.sendKeys(organization);
        return this;
    }

    public PartnerCreationPage enterPositionInOrganization(String position) {
        logger.info("Position in organization was typed");
        this.positionInOrganization.sendKeys(position);
        return this;
    }

    @Step("Click Submit button")
    //This method submits the partner creation form and returns success page in case of success registration or current page is case of any error
    public BasePage submit(){
        logger.info("Submit button was clicked");
        submitButton.click();

        String title = $("title").getText();
        logger.info("A title of the current page is got");
        if (title.equals("Partner registration") || title.equals("Регистрация партнера") || title.equals("Реєстрація партнера")) {
            return this;
        } else {
            return new SuccessRegistrationPage();
        }
    }
    @Step("Fill mandatory fields for registration")
    //This method fills the mandatory fields for registration with provided values
    public PartnerCreationPage fillInMandatoryFields(Partner partner) {
        enterFirstName(partner.getFirstName());
        enterLastName(partner.getLastName());
        enterEmail(partner.getEmail());
        selectRadioButton(partner.getGender());
        enterPassword(partner.getPassword());
        enterConfirmPassword(partner.getConfirmPassword());
        enterOrganization(partner.getOrganizationName());
        enterPositionInOrganization(partner.getOrganizationPosition());
        return this;
    }
}
