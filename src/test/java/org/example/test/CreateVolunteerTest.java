package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CreateVolunteerTest {

    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";
    private  String successPageUrl = "https://skarb.foxminded.ua/registration/result/success";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void createVolunteerWithValidValues() {
        Volunteer volunteer = new Volunteer("Sara", "Konnor", "email" + Math.random() + "@gmail.com",
                "+380984444444", "PA$$word123", "PA$$word123");
        goToVolunteersCreationPage();
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement phoneNumber = driver.findElement(By.id("phoneNumber"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        WebElement submitButton = driver.findElement(By.className("btn-success"));
        firstName.sendKeys(volunteer.getFirstName());
        lastName.sendKeys(volunteer.getLastName());
        email.sendKeys(volunteer.getEmail());
        phoneNumber.sendKeys(volunteer.getPhoneNumber());
        password.sendKeys(volunteer.getPassword());
        confirmPassword.sendKeys(volunteer.getConfirmPassword());
        submitButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), successPageUrl);
        WebElement successMessage = driver.findElement(By.name("message"));
        Assert.assertEquals(successMessage.getText(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");
    }

    @Test
    public void createVolunteerWithInvalidEmail() {
        Volunteer volunteer = new Volunteer("Sara", "Konnor", "test",
                "+380984444444", "PA$$word123", "PA$$word123");
        goToVolunteersCreationPage();
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement phoneNumber = driver.findElement(By.id("phoneNumber"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        WebElement submitButton = driver.findElement(By.className("btn-success"));
        firstName.sendKeys(volunteer.getFirstName());
        lastName.sendKeys(volunteer.getLastName());
        email.sendKeys(volunteer.getEmail());
        phoneNumber.sendKeys(volunteer.getPhoneNumber());
        password.sendKeys(volunteer.getPassword());
        confirmPassword.sendKeys(volunteer.getConfirmPassword());
        submitButton.click();
        WebElement error = driver.findElement(By.className("text-danger"));
        Assert.assertEquals(error.getText(), "Email is incorrect");
    }

    @Test
    public void createVolunteerWithEmptyFields() {
        goToVolunteersCreationPage();
        WebElement submitButton = driver.findElement(By.className("btn-success"));
        submitButton.click();
        List<WebElement> errors = driver.findElements(By.className("text-danger"));
        Assert.assertEquals(errors.size(), 5);
        for (int i = 0; i < errors.size(); i++) {
            Assert.assertEquals(errors.get(i).getText(), "Field can`t be empty");
        }
    }
    private void goToVolunteersCreationPage() {
        WebElement addUserItem = driver.findElement(By.className("fa-user-plus"));
        addUserItem.click();
        WebElement createVolunteerButton = driver.findElement(By.className("btn-primary"));
        createVolunteerButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
