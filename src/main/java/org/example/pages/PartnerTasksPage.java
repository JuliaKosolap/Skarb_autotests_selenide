package org.example.pages;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PartnerTasksPage extends NavigationMenu {

    @FindBy(css = "a[aria-label='Next']")
    private WebElement nextPageLink;

    @FindBy(xpath = "//nav[@id='paginationContainer']//li[1]")
    private WebElement firstPaginationItem;
    @FindBy(xpath = "//nav[@id='paginationContainer']//li[9]")
    private WebElement lastPaginationItem;

    public PartnerTasksPage(WebDriver driver) {
        super(driver);
    }

    //This method clicks on the Next link in the pagination section until the provided number of page is reached
    // and returns the names of the Tasks from all these pages.
    public ArrayList<String> getListOfTasksForGivenNumberOfPages(int numberOfPage){
        ArrayList<String> overallTasks = new ArrayList<>();

        int index = 1;
        while (index < numberOfPage) {
            List<String> listOfTasksOnCurrentPage = getListOfTasksOnCurrentPage();
            overallTasks.addAll(listOfTasksOnCurrentPage);
            driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
            index++;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        }
        return overallTasks;
    }

    //this method waits until all tasks on the current page are loaded,
    // then returns the names of the tasks on the current page as a list
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

    //this method returns the number of the last page of the tasks
    public String getTheLastPageNumber() {
        List<WebElement> paginationItems = driver.findElements(By.className("page-link"));
        String lastPageNumber = paginationItems.get(6).getText();
        return lastPageNumber;
    }

    //this method returns the names of the tasks on the last page
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

}
