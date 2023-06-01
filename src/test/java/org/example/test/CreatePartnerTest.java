package org.example.test;

import entity.Gender;
import entity.Partner;
import entity.Volunteer;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.helpers.PasswordGeneratingUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatePartnerTest {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";
    private String successPageUrl = "https://skarb.foxminded.ua/registration/result/success";
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
        WebElement createVolunteerButton = driver.findElement(By.xpath("//button[@class='btn btn-success btn-lg btn-block']"));
        createVolunteerButton.click();
    }

    @Test
    public void createPartnerWithValidValues() {
        Partner partner = new Partner(email, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);
        goToPartnerCreationPage();
        fillInMandatoryFields(partner);
        submit();
        WebElement successMessage = driver.findElement(By.cssSelector("div[id='content']"));
        Assert.assertEquals(successMessage.getText(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");

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
