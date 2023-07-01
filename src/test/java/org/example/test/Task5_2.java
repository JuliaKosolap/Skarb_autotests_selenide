package org.example.test;

import org.example.pages.HomePage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import static common.CustomLogger.logger;

@Listeners(common.CustomListener.class)

public class Task5_2 extends BaseTest {
    String baseUrl = "https://skarb.foxminded.ua/";


    @Test
    public void goToAboutUsPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.goToAboutUsPage();
        logger.info("About Us menu item was selected");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/about");
    }
    @Test
    public void goToNewsPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.goToNewsPage();
        logger.info("News menu item was selected");

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "news");
    }

    @Test
    public void goToRulesPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        logger.info("Home page was opened");

        homePage.goToRulesPage();
        logger.info("Rules menu item was selected");

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/rules");
    }
     @Test
     public void goToHelpPage() {
         HomePage homePage = new HomePage(driver);
         Assert.assertTrue(homePage.isInitialized());
         logger.info("Home page was opened");

         homePage.goToHelpPage();
         logger.info("Help menu item was selected");

         Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/help");
     }
}
