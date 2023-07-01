package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.Language;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static common.CustomLogger.logger;


@Listeners(common.CustomListener.class)

public class Task5_3 extends BaseTest {
    @Test
    public void switchToUkrainian() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.selectLanguage(Language.UA);
        logger.info("Ukrainian language was selected");

        Assert.assertEquals(homePage.getPageHeader(), "Довгий заголовок на два рядки");
    }

    @Test
    public void switchToRussian() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.selectLanguage(Language.RU);
        logger.info("Russian language was selected");

        Assert.assertEquals(homePage.getPageHeader(), "Длинный заголовок в 2 строчки");
    }

    @Test
    public void switchToEnglish() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.selectLanguage(Language.EN);
        logger.info("English language was selected");

        Assert.assertEquals(homePage.getPageHeader(), "Long caption for 2 rows");

    }
}
