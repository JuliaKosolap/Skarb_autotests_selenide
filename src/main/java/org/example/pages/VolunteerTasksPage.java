package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VolunteerTasksPage extends NavigationMenu {
    public VolunteerTasksPage(WebDriver driver) {
        super(driver);
    }

    //this method returns the names of the tasks on the last page
    public List<String> getListOfTasksOnLastPage() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("page-link")));

        //from time to time the StaleElementReferenceException is thrown so I do 2 attempts to click on the link
        int attemptCount = 2;
        int startIndex = 0;
        while (startIndex < attemptCount) {
            try {
                driver.findElements(By.className("page-link")).get(6).click();
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
                startIndex++;
            }
        }
        //from time to time the StaleElementReferenceException is thrown if element is not loaded yet so I catch this exception
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.attributeContains(By
                    .xpath("//nav[@id='paginationContainer']//li[9]"), "class", "page-item disabled"));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return getListOfTasksOnCurrentPage();
    }
    //this method waits until all tasks on the current page are loaded,
    // then returns the names of the tasks on the current page as a list
    public List<String> getListOfTasksOnCurrentPage() {
        ArrayList<String> tasks = new ArrayList<>();

        //from time to time the StaleElementReferenceException is thrown if cards are not loaded yet
        // so I catch this exception and do one more attempt to find elements
        int attemptCount = 2;
        int startIndex = 0;
        List<WebElement> searchResultContainer = null;
        while (startIndex < attemptCount) {
            try {
                searchResultContainer = driver.findElements(By.xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5"));
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
                startIndex++;
            }
        }
        for (int i = 0; i < searchResultContainer.size(); i++) {
            //from time to time the StaleElementReferenceException is thrown if cards are not loaded yet so I catch this exception
            try {
                String text = driver.findElements(By.
                        xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5")).get(i).getText();
                tasks.add(text);
            } catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } return tasks;
    }

    public void printTheNamesOfTheTasks(List<String> taskNames) {
        for (int i = 0; i < taskNames.size(); i++) {
            System.out.println("Task #" + (i + 1) + ": " + taskNames.get(i));
        }
    }
}
