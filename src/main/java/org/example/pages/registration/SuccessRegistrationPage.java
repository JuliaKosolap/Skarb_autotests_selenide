package org.example.pages.registration;

import org.example.pages.NavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

public class SuccessRegistrationPage extends NavigationMenu {

    @FindBy(className = "display-3")
    private WebElement successMessage;

    public SuccessRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        logger.info("Success registration page is loaded");
        return successMessage.isDisplayed();
    }

    public String getMessage(){
        logger.info("Success message is received");
        return successMessage.getText();
    }
}
