package org.example.pages.registration;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.pages.NavigationMenu;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class RegistrationPage extends NavigationMenu {
    SelenideElement header =  $(byAttribute("name", "registration-text"));
    SelenideElement createVolunteerButton = $(byAttribute("name","volunteers"));
    SelenideElement createPartnerButton = $(byAttribute("name","partners"));
    SelenideElement createOrganizationButton = $(byAttribute("name","organizations"));

    public boolean isInitialized() {
        logger.info("Registration page is loaded");
        return header.isDisplayed();
    }
    public String getHeader() {
        logger.info("The header of the Registration page is received");
        return header.getText();
    }
    @Step("Open Volunteer Registration Page")
    public VolunteerCreationPage goToVolunteerCreationPage() {
        logger.info("Create volunteer button is clicked");
        createVolunteerButton.click();
        return new VolunteerCreationPage();
    }
    @Step("Open Partner Registration Page")
    public PartnerCreationPage goToPartnerCreationPage() {
        logger.info("Create partner button is clicked");
        createPartnerButton.click();
        return new PartnerCreationPage();
    }
    @Step("Open Organization Registration Page")
    public OrganizationRegistrationPage goToOrganizationRegistrationPage() {
        logger.info("Create organization button is clicked");
        createOrganizationButton.click();
        return new OrganizationRegistrationPage();
    }
}
