package org.example.test;

import entity.Gender;
import entity.Partner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test_data.RandomData;

public class Task6 {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";
    private String successPageUrl = "https://skarb.foxminded.ua/registration/result/success";
    private String firstName = RandomData.randomFirstOrLastName(8);
    private String lastName = RandomData.randomFirstOrLastName(8);
    private String email = RandomData.randomCorporateEmail();
    private String password = RandomData.randomPassword(8);
    private String confirmPassword = password;
    private String organization = RandomData.randomString(10);
    private String positionInOrganization = RandomData.randomString(10);

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    //This method opens the Registration page and then opens the Create Partner page
    private void goToPartnerCreationPage() {
        WebElement addUserItem = driver.findElement(By.className("fa-user-plus"));
        addUserItem.click();
        WebElement createPartnerButton = driver.findElement(By.xpath("//button[@class='btn btn-success btn-lg btn-block']"));
        createPartnerButton.click();
    }

    //This test creates a partner with valid values and verifies if success message appeared
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
    //This method fills the mandatory fields for registration with provided values
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

    //This method submits the partner creation form
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
