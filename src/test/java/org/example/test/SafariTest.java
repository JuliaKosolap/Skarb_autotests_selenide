package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SafariTest {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeMethod
    public void setUp() {
        driver = new SafariDriver();
        driver.get(baseUrl);
    }
    @Test
    public void goToHomePageTest() {
        WebElement title = driver.findElement(By.cssSelector("h4.text-dark-red"));
        Assert.assertEquals(title.getText(), "SKARB NGO");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
