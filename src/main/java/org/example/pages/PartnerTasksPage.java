package org.example.pages;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.example.common.CustomLogger.logger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class PartnerTasksPage extends NavigationMenu {

    public PartnerTasksPage(WebDriver driver) {
        super(driver);
    }

    //This method clicks on the Next link in the pagination section until the provided number of page is reached
    // and returns the names of the Tasks from all these pages.
    public ArrayList<String> getListOfTasksForGivenNumberOfPages(int numberOfPage) {
        ArrayList<String> overallTasks = new ArrayList<>();

        int index = 1;
        while (index <= numberOfPage) {
            List<String> listOfTasksOnCurrentPage = getListOfTasksOnCurrentPage();
            logger.info("The list of the tasks was received");
            overallTasks.addAll(listOfTasksOnCurrentPage);

            //When the last page is reached the StaleElementReferenceException is thrown if try to click on Next because it is disabled
            //so I catch this exception
            try {
                logger.info("'Next' link is clicked");
                driver.findElement(By.cssSelector("a[aria-label='Next']")).click();

                logger.info("Waiting for the next page is loaded");
                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.urlContains("page=" + index));
                index++;
            } catch (ElementClickInterceptedException e) {
                e.printStackTrace();
                break;
            }
        }
        return overallTasks;
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
                logger.info("Trying to get the list of the tasks on the current page");
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
                logger.info("Getting the names of the tasks");
                String text = driver.findElements(By.
                        xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5")).get(i).getText();
                tasks.add(text);
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        } return tasks;
    }

    //this method returns the number of the last page of the tasks
    public String getTheLastPageNumber() {
        logger.info("Trying to find pagination");
        List<WebElement> paginationItems = driver.findElements(By.className("page-link"));

        logger.info("The last page is number is got");
        String lastPageNumber = paginationItems.get(6).getText();
        return lastPageNumber;
    }

    //this method returns the names of the tasks on the last page
    public List<String> getListOfTasksOnLastPage() {
        logger.info("Waiting for pagination to be loaded");
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("page-link")));

        //from time to time the StaleElementReferenceException is thrown so I do 2 attempts to click on the link
        int attemptCount = 2;
        int startIndex = 0;
        while (startIndex < attemptCount) {
            try {
                logger.info("Last page link is clicked");
                driver.findElements(By.className("page-link")).get(6).click();
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
                startIndex++;
            }
        }
        //from time to time the StaleElementReferenceException is thrown if element is not loaded yet so I catch this exception
        try {
            logger.info("Waiting for the last page to be loaded");
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.attributeContains(By
                    .xpath("//nav[@id='paginationContainer']//li[9]"), "class", "page-item disabled"));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
            logger.info("The list of the tasks on the last page are received");
            return getListOfTasksOnCurrentPage();
        }

    public void printTheNamesOfTheTasks(List<String> taskNames) {
        for (int i = 0; i < taskNames.size(); i++) {
            logger.info("The names of the tasks are printed into console"
            );
            System.out.println("Task #" + (i + 1) + ": " + taskNames.get(i));
        }
    }

}
