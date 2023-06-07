package org.example.test;

import entity.Gender;
import entity.Partner;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.helpers.PasswordGeneratingUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Task7 {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";
    private String mailHogUrl = "http://185.149.40.46:8025/";
    private String firstName = "Test" + RandomStringUtils.randomAlphabetic(10).toLowerCase();
    private String lastName = "Test" + RandomStringUtils.randomAlphabetic(10).toLowerCase();
    private String email = RandomStringUtils.randomAlphanumeric(10) + "@skarb.ngo";
    private String password = PasswordGeneratingUtil.generatePassword();
    private String confirmPassword = password;
    private String organization = RandomStringUtils.randomAlphabetic(10).toLowerCase();
    private String positionInOrganization = RandomStringUtils.randomAlphabetic(10).toLowerCase();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    private void goToPartnerCreationPage() {
        WebElement addUserItem = driver.findElement(By.className("fa-user-plus"));
        addUserItem.click();
        WebElement createPartnerButton = driver.findElement(By.xpath("//button[@class='btn btn-success btn-lg btn-block']"));
        createPartnerButton.click();
    }

    @Test
    public void createPartnerWithValidValues() throws InterruptedException {
        Partner partner = new Partner(email, firstName, lastName, Gender.MALE, password, confirmPassword, organization, positionInOrganization);
        goToPartnerCreationPage();
        fillInMandatoryFields(partner);
        submit();
        driver.get(mailHogUrl);
        confirmRegistration(partner);
        WebElement successMessage = driver.findElement(By.className("display-3"));
        Assert.assertEquals(successMessage.getText(), "Your email confirmed!");
    }

    private void confirmRegistration(Partner partner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='msglist-message row ng-scope']//div[@class='ng-binding ng-scope']"),
                partner.getEmail()));
        List<WebElement> msgRows = driver.findElements(By.xpath("//div[@class='msglist-message row ng-scope']"));
        for (int i = 0; i < msgRows.size(); i++) {
            WebElement email = msgRows.get(i).findElement(By.xpath("//div[@class='ng-binding ng-scope']"));
            if (email.getText().equals(partner.getEmail())) {
                WebElement registrationConfirmation = msgRows.get(i).findElement(By.xpath("//span[@class='subject unread ng-binding']"));
                registrationConfirmation.click();
                WebElement confirmationLink = driver.findElement(By.xpath("//div[@id='preview-plain']//a"));
                confirmationLink.click();
                String parentPage = driver.getWindowHandle();
                Set<String> s = driver.getWindowHandles();
                Iterator<String> I1 = s.iterator();
                while (I1.hasNext()) {
                    String child_window = I1.next();
                    if (!parentPage.equals(child_window)) {
                        driver.switchTo().window(child_window);
                    }
                }
                break;
            }
        }
    }

    private void fillInMandatoryFields(Partner partner) {
        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement genderFemale = driver.findElement(By.cssSelector("input[type='radio'][value='FEMALE']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        WebElement organization = driver.findElement(By.cssSelector("input[id='organizationName']"));
        WebElement positionInOrganization = driver.findElement(By.cssSelector("input[id='positionInOrganization"));
        email.sendKeys(partner.getEmail());
        firstName.sendKeys(partner.getFirstName());
        lastName.sendKeys(partner.getLastName());
        genderFemale.click();
        password.sendKeys(partner.getPassword());
        confirmPassword.sendKeys(partner.getConfirmPassword());
        organization.sendKeys(partner.getOrganizationName());
        positionInOrganization.sendKeys(partner.getOrganizationPosition());
    }

    private void submit() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
