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

    public List<String> getListOfTasksOnLastPage() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("page-link")));

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
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.attributeContains(By
                .xpath("//nav[@id='paginationContainer']//li[9]"), "class", "page-item disabled"));
        return getListOfTasksOnCurrentPage();
    }
    public List<String> getListOfTasksOnCurrentPage() {
        ArrayList<String> tasks = new ArrayList<>();

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
            try {
                String text = driver.findElements(By.
                        xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5")).get(i).getText();
                tasks.add(text);
            } catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } return tasks;
    }
}
