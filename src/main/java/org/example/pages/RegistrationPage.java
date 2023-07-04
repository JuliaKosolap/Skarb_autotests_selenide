package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

public class RegistrationPage extends NavigationMenu {
    @FindBy(name = "registration-text")
    private WebElement header;
    @FindBy(name = "volunteers")
    private WebElement createVolunteerButton;
    @FindBy(name = "partners")
    private WebElement createPartnerButton;
    @FindBy(name = "organizations")
    private WebElement createOrganizationButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    public boolean isInitialized() {
        logger.info("Registration page is loaded");
        return header.isDisplayed();
    }
    public String getHeader() {
        logger.info("The header of the Registration page is received");
        return header.getText();
    }
    public VolunteerCreationPage goToVolunteerCreationPage() {
        logger.info("Create volunteer button is clicked");
        createVolunteerButton.click();
        return new VolunteerCreationPage(driver);
    }
    public PartnerCreationPage goToPartnerCreationPage() {
        logger.info("Create partner button is clicked");
        createPartnerButton.click();
        return new PartnerCreationPage(driver);
    }
}
