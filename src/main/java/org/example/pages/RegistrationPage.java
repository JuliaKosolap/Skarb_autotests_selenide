package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return header.isDisplayed();
    }
    public String getHeader() {
        return header.getText();
    }
    public VolunteerCreationPage goToVolunteerCreationPage() {
        createVolunteerButton.click();
        return new VolunteerCreationPage(driver);
    }
    public PartnerCreationPage goToPartnerCreationPage() {
        createPartnerButton.click();
        return new PartnerCreationPage(driver);
    }
}
