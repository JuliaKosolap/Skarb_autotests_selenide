package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OptionalTask_CalculateTasksOnFivePages {
    private WebDriver driver;
    private String baseUrl = "https://skarb.foxminded.ua/";
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    //This method opens the Volunteer Tasks Page
    private void goToPartnersTasksPage() {
        WebElement tasksMenuItem = driver.findElement(By.id("tasksDropdown"));
        tasksMenuItem.click();
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector("a.dropdown-item.link"));
        for (int i = 0; i < dropdownItems.size(); i++) {
            if (dropdownItems.get(i).getText().equals("For partners") || dropdownItems.get(i).getText().equals("Для партнеров")
                    || dropdownItems.get(i).getText().equals("Для партнерів")) {
                dropdownItems.get(i).click();
                break;
            }
        }
    }
    @Test
    public void calculateNumberOfTasksOnFivePages() {
        goToPartnersTasksPage();
        ArrayList<String> listOfTasks = getListOfTasksForGivenNumberOfPages(5);
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println("Task #" + (i + 1) + ": " + listOfTasks.get(i));
        }
        System.out.println("Overall number of tasks on 5 pages is: " + listOfTasks.size());

    }
    //This method clicks on the Next link in the pagination section until the provided number of page is reached
    // and returns the names of the Tasks from these pages
    public ArrayList<String> getListOfTasksForGivenNumberOfPages(int numberOfPage) {
        ArrayList<String> taskNames = new ArrayList<>();
        for (int i = 0; i < numberOfPage; i++) {
            List<WebElement> tasks = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[name='task-header']")));
            for (int j = 0; j <  tasks.size() ; j++) {
                String taskName = tasks.get(i).getText();
                taskNames.add(taskName);
            }
            WebElement nextPageLink  = new WebDriverWait(driver, Duration.ofSeconds(30)).
                    until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label='Next']")));
            nextPageLink.click();
        }
        return taskNames;
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
