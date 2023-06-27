package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailHogPage extends BasePage {

    public MailHogPage(WebDriver driver) {
        super(driver);
    }

    //This method waits for new record to appear in the list of registered users;
    public MailHogPage waitForNewMessageToAppear(String emailAddress) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='msglist-message row ng-scope']//div[@class='ng-binding ng-scope']"),
                emailAddress));
        return this;
    }

    //This method opens new email and clicks on the Registration confirmation link
    public void confirmRegistrationOfNewPartner(String emailAddress) {
        List<WebElement> msgRows = driver.findElements(By.xpath("//div[@class='msglist-message row ng-scope']"));
        for (int i = 0; i < msgRows.size(); i++) {
            WebElement email = msgRows.get(i).findElement(By.xpath("//div[@class='ng-binding ng-scope']"));
            if (email.getText().equals(emailAddress)) {
                WebElement registrationConfirmation = msgRows.get(i).findElement(By.xpath("//span[@class='subject unread ng-binding']"));
                registrationConfirmation.click();
                WebElement confirmationLink = driver.findElement(By.xpath("//div[@id='preview-plain']//a"));
                confirmationLink.click();
            }
            break;
        }
    }
}
