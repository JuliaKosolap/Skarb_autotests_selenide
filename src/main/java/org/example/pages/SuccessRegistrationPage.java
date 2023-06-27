package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessRegistrationPage extends NavigationMenu {

    @FindBy(className = "display-3")
    private WebElement successMessage;

    public SuccessRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return successMessage.isDisplayed();
    }

    public String getMessage(){
        return successMessage.getText();
    }
}
