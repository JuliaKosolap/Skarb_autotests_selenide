package org.example.pages.registration;

import com.codeborne.selenide.SelenideElement;
import org.example.pages.BasePage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class SuccessRegistrationPage extends BasePage {

    SelenideElement successMessage = $(byAttribute("class", "display-3 text-center"));

    public boolean isInitialized() {
        logger.info("Success registration page is loaded");
        return successMessage.isDisplayed();
    }
    public SelenideElement getSuccessMessage() {
        logger.info("Success message is received");
        return successMessage;
    }
}
