package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OptionalTaskCalculateTasks {
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
    public void calculateNumberOfTasks() {
        goToPartnersTasksPage();
        clickOnShowMoreLink();
        ArrayList<String> taskNames = new ArrayList<>();
        List<WebElement> taskCards = driver.findElements(By.name("task-card"));
        for (int i = 0; i < taskCards.size(); i++) {
                String taskName = taskCards.get(i).findElement(By.className("col-9")).getText();
                taskNames.add(taskName);
                System.out.println("Task #" + (i + 1) + ": " + taskName);
        }
        System.out.println("Overall number of tasks is: " + taskNames.size());
    }

    //This method clicks on the Show More link until all task tiles are displayed on the page and Show More link becomes invisible
    private void clickOnShowMoreLink() {
        WebElement showMoreLinkHolder = driver.findElement(By.id("showMoreLinkHolder"));
        WebElement showMoreLink = driver.findElement(By.id("showMoreLink"));
        while (!showMoreLinkHolder.getAttribute("class").equals("row mt-3 d-none")) {
            try {
                showMoreLink.click();
            } catch (ElementNotInteractableException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
