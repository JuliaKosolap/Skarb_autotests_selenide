package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.Language;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task5_3 extends BaseTest {


    @Test
    public void switchToUkrainian() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.selectLanguage(Language.UA);
        Assert.assertEquals(homePage.getPageHeader(), "Довгий заголовок на два рядки");
    }

    @Test
    public void switchToRussian() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.selectLanguage(Language.RU);
        Assert.assertEquals(homePage.getPageHeader(), "Длинный заголовок в 2 строчки");
    }

    @Test
    public void switchToEnglish() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.selectLanguage(Language.EN);
        Assert.assertEquals(homePage.getPageHeader(), "Long caption for 2 rows");

    }
}
