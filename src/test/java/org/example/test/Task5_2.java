package org.example.test;

import org.example.common.CustomListener;
import org.example.common.Props;
import org.example.pages.HomePage;
import org.example.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.example.common.CustomLogger.logger;

@Listeners(CustomListener.class)

public class Task5_2 extends BaseTest {

    @Test
    public void goToAboutUsPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();

        logger.info("About Us menu item was selected");
        homePage.goToAboutUsPage();
        webdriver().shouldHave(url(Props.siteUrl + "static/about"));

    }
    @Test
    public void goToNewsPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();

        logger.info("News menu item was selected");
        homePage.goToNewsPage();

        webdriver().shouldHave(url(Props.siteUrl + "news"));

    }

    @Test
    public void goToRulesPage() {
        logger.info("Home page was opened");
        HomePage homePage = new HomePage();


        logger.info("Rules menu item was selected");
        homePage.goToRulesPage();

        webdriver().shouldHave(url(Props.siteUrl + "static/rules"));

    }
     @Test
     public void goToHelpPage() {
         logger.info("Home page was opened");
         HomePage homePage = new HomePage();

         logger.info("Help menu item was selected");
         homePage.goToHelpPage();

         webdriver().shouldHave(url(Props.siteUrl + "static/help"));

     }
}
