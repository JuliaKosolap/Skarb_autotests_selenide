package org.example.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.Set;
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
