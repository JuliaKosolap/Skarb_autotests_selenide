package org.example.pages.registration;

import io.qameta.allure.Step;
import org.example.pages.BasePage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class MailHogPage extends BasePage {

    @Step("Confirm registration")
    //This method opens new email and clicks on the Registration confirmation link
    public void confirmRegistrationOfNewPartner(String emailAddress) {

                logger.info("New email is found and opened");
                $(byText(emailAddress)).click();

                logger.info("Registration Confirmation link is clicked");
                $(byXpath("//div[@id='preview-plain']//a")).click();

    }
    @Step("Confirm to become task performer")
    //This method opens new email and clicks on the Registration confirmation link
    public void confirmToBecomeTaskPerformer(String emailAddress) {

        logger.info("New email is found");


        logger.info("New email is opened");


        logger.info("Invitation Confirmation link is clicked");
    }
}
