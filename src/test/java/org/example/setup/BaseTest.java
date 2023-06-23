package org.example.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeClass
    public static void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @BeforeMethod
    public void testSetUp() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown(){
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
