package org.example.test;

import org.example.pages.HomePage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class Task5_2 extends BaseTest {
    String baseUrl = "https://skarb.foxminded.ua/";


    @Test
    public void goToAboutUsPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.goToAboutUsPage();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/about");
    }
    @Test
    public void goToNewsPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.goToNewsPage();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "news");
    }

    @Test
    public void goToRulesPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        homePage.goToRulesPage();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/rules");
    }
     @Test
     public void goToHelpPage() {
         HomePage homePage = new HomePage(driver);
         Assert.assertTrue(homePage.isInitialized());
         homePage.goToHelpPage();
         Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "static/help");
     }
}
