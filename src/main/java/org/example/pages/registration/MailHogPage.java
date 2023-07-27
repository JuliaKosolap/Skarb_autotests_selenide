package org.example.pages.registration;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.common.CustomLogger.logger;

public class MailHogPage extends BasePage {

    public MailHogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to MailHog and wait for new registration message to be received")
    //This method waits for new record to appear in the list of registered users;
    public MailHogPage waitForNewMessageToAppear(String emailAddress) {
        logger.info("New email is being waited on MailHog");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='msglist-message row ng-scope']//div[@class='ng-binding ng-scope']"),
                emailAddress));
        return this;
    }

    @Step("Confirm registration")
    //This method opens new email and clicks on the Registration confirmation link
    public void confirmRegistrationOfNewPartner(String emailAddress) {
        logger.info("Trying to get the list of emails in MailHog");
        List<WebElement> msgRows = driver.findElements(By.xpath("//div[@class='msglist-message row ng-scope']"));
        logger.info("The list of emails is got");

        for (int i = 0; i < msgRows.size(); i++) {
            logger.info("Searching the new email for newly created partner");
            WebElement email = msgRows.get(i).findElement(By.xpath("//div[@class='ng-binding ng-scope']"));

            if (email.getText().equals(emailAddress)) {
                logger.info("New email is found");
                WebElement registrationConfirmation = msgRows.get(i).findElement(By.xpath("//span[@class='subject unread ng-binding']"));

                logger.info("New email is opened");
                registrationConfirmation.click();

                WebElement confirmationLink = driver.findElement(By.xpath("//div[@id='preview-plain']//a"));
                logger.info("Registration Confirmation link is clicked");
                confirmationLink.click();
            }
            break;
        }
    }
}
