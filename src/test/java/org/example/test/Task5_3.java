package org.example.test;

import org.example.common.CustomListener;
import org.example.pages.HomePage;
import org.example.pages.Language;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.example.common.CustomLogger.logger;


@Listeners(CustomListener.class)

public class Task5_3 extends BaseTest {
    @Test
    public void switchToUkrainian() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isInitialized());

        logger.info("Ukrainian language was selected");
        homePage.selectLanguage(Language.UA);

        Assert.assertEquals(homePage.getPageHeader(), "Довгий заголовок на два рядки");
    }

    @Test
    public void switchToEnglish() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isInitialized());

        logger.info("English language was selected");
        homePage.selectLanguage(Language.EN);

        Assert.assertEquals(homePage.getPageHeader(), "Long caption for 2 rows");

    }
    @Test
    public void switchToRussian() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isInitialized());

        logger.info("Russian language was selected");
        homePage.selectLanguage(Language.RU);

        Assert.assertEquals(homePage.getPageHeader(), "Длинный заголовок в 2 строчки");
    }
}
