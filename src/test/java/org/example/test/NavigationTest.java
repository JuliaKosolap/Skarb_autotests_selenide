package org.example.test;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class NavigationTest {
    private WebDriver driver;
    String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void goToAboutUsPage() {
        WebElement aboutProjectNavButton = driver.findElement(By.id("aboutDropdown"));
        aboutProjectNavButton.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("About us")) {
                dropdownItems.get(i).click();
                Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/about");
                WebElement title = driver.findElement(By.className("title"));
                Assert.assertEquals(title.getText(), "About project");
                break;
            }
        }
    }
    @Test
    public void goToNewsPage() {
        WebElement aboutProjectNavButton = driver.findElement(By.id("aboutDropdown"));
        aboutProjectNavButton.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("News")) {
                dropdownItems.get(i).click();
                Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "news");
                break;
            }
        }
    }

    @Test
    public void goToRulesPage() {
        WebElement aboutProjectNavButton = driver.findElement(By.id("aboutDropdown"));
        aboutProjectNavButton.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("Rules")) {
                dropdownItems.get(i).click();
                Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/rules");
                WebElement title = driver.findElement(By.className("title"));
                Assert.assertEquals(title.getText(), "Rules");
                break;
            }
        }
    }
     @Test
     public void goToHelpPage() {
         WebElement aboutProjectNavButton = driver.findElement(By.id("aboutDropdown"));
         aboutProjectNavButton.click();
         List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
         for (int i = 0; i < dropdownItems.size(); i++) {
             if (dropdownItems.get(i).getText().equals("Help")) {
                 dropdownItems.get(i).click();
                 Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/help");
                 WebElement title = driver.findElement(By.className("title"));
                 Assert.assertEquals(title.getText(), "Help");
                 break;
             }
         }
     }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
