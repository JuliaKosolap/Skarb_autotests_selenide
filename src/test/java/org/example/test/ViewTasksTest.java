package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ViewTasksTest {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }
    @Test
    public void goToViewTasksPageTest() throws InterruptedException {
        WebElement buttonViewTasks = driver.findElement(By.cssSelector("button.button.statistic_button.button--orange"));
        buttonViewTasks.click();
        Assert.assertEquals(driver.getTitle(), "Task page");
    }
    @Test
    public void checkInitialTasksAmountTest() throws InterruptedException {
        goToViewTasksPageTest();
        List<WebElement> tasks = driver.findElements(By.cssSelector("div.card.task-card.h-100.mr-3"));
        Assert.assertEquals(tasks.size(),6);

    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
