package org.example.setup;

import io.qameta.allure.Step;
import org.example.pages.HomePage;
import org.example.pages.Language;
import org.example.pages.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.example.common.Props.initProperties;
import static org.example.common.Props.resetProperties;


public class BaseTest {
    protected static WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeSuite
    public void setUp(){
        driver = WebDriverSingleton.getInstance();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Step("Start browser and open Home Page")
    @BeforeMethod
    public void testSetUp() throws IOException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        new HomePage(driver).selectLanguage(Language.EN);
        initProperties();
    }

    @AfterSuite
    public void tearDown(){
        resetProperties();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
    @Step("Return to the main site")
    //method that allows to switch between windows in a current browser
    public void switchBetweenWindows() {
        String parentPage = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parentPage.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
    }
}
