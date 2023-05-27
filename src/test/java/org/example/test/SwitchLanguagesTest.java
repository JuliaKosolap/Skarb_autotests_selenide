package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SwitchLanguagesTest {
    private WebDriver driver;
    String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void switchToUkrainian() {
        WebElement languageSwitcher = driver.findElement(By.id("langDropdown"));
        languageSwitcher.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("UA") || dropdownItems.get(i).getText().equals("Укр")) {
                dropdownItems.get(i).click();
                WebElement title = driver.findElement(By.className("info_title"));
                Assert.assertEquals(title.getText(), "Довгий заголовок на два рядки");
                break;
            }
        }
    }

    @Test
    public void switchToRussian() {
        WebElement languageSwitcher = driver.findElement(By.id("langDropdown"));
        languageSwitcher.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("RU") || dropdownItems.get(i).getText().equals("Рос")) {
                dropdownItems.get(i).click();
                WebElement title = driver.findElement(By.className("info_title"));
                Assert.assertEquals(title.getText(), "Длинный заголовок в 2 строчки");
                break;
            }
        }
    }

    @Test
    public void switchToEnglish() {
        WebElement languageSwitcher = driver.findElement(By.id("langDropdown"));
        languageSwitcher.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("EN") || dropdownItems.get(i).getText().equals("Англ")) {
                dropdownItems.get(i).click();
                WebElement title = driver.findElement(By.className("info_title"));
                Assert.assertEquals(title.getText(), "Long caption for 2 rows");
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
