package org.example.test;

import org.example.common.CustomListener;
import org.example.pages.HomePage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.example.common.CustomLogger.logger;

@Listeners(CustomListener.class)

public class Task5_2 extends BaseTest {
    String baseUrl = "https://skarb.foxminded.ua/";


    @Test
    public void goToAboutUsPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        logger.info("About Us menu item was selected");
        homePage.goToAboutUsPage();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/about");
    }
    @Test
    public void goToNewsPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        logger.info("News menu item was selected");
        homePage.goToNewsPage();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "news");
    }

    @Test
    public void goToRulesPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        logger.info("Rules menu item was selected");
        homePage.goToRulesPage();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/rules");
    }
     @Test
     public void goToHelpPage() {
         logger.info("Home page was opened");
         HomePage homePage = new HomePage(driver);
         Assert.assertTrue(homePage.isInitialized());

         logger.info("Help menu item was selected");
         homePage.goToHelpPage();

         Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/help");
     }
}
