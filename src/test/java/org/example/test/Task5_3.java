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

public class Task5_3 {
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
        selectLanguage(Language.UA, Language.Укр);
        WebElement title = driver.findElement(By.className("info_title"));
        Assert.assertEquals(title.getText(), "Довгий заголовок на два рядки");
    }

    @Test
    public void switchToRussian() {
        selectLanguage(Language.RU, Language.Рос);
        WebElement title = driver.findElement(By.className("info_title"));
        Assert.assertEquals(title.getText(), "Длинный заголовок в 2 строчки");
    }

    @Test
    public void switchToEnglish() {
        selectLanguage(Language.EN, Language.Англ);
        WebElement title = driver.findElement(By.className("info_title"));
        Assert.assertEquals(title.getText(), "Long caption for 2 rows");

    }

    private void selectLanguage(Language langLatin, Language langCyrillic) {
        WebElement languageSwitcher = driver.findElement(By.id("langDropdown"));
        languageSwitcher.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals(langLatin.toString()) || dropdownItems.get(i).getText().equals(langCyrillic.toString())) {
                dropdownItems.get(i).click();
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
