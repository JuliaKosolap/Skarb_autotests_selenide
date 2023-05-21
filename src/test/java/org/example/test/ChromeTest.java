package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ChromeTest {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }
    @Test
    public void goToHomePageTest() {
        WebElement title = driver.findElement(By.cssSelector("h4.text-dark-red"));
        Assert.assertEquals(title.getText(), "SKARB NGO");
    }
    @Test
    public void checkContactUsTest() throws InterruptedException {
        WebElement contactUs = driver.findElement(By.partialLinkText("Contact us"));
        contactUs.click();
        WebElement page = driver.findElement(By.cssSelector("title.info_title.title--red"));
        Assert.assertEquals(page.getText(), "Contact us");

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
