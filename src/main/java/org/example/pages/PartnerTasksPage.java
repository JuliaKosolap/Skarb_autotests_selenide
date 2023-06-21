package org.example.pages;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PartnerTasksPage extends NavigationMenu {

    @FindBy(css = "a[aria-label='Next']")
    private WebElement nextPageLink;

    public PartnerTasksPage(WebDriver driver) {
        super(driver);
    }

    //This method clicks on the Next link in the pagination section until the provided number of page is reached
    // and returns the names of the Tasks from these pages.
    public ArrayList<String> getListOfTasksForGivenNumberOfPages(int numberOfPage) throws InterruptedException {
        ArrayList<String> overallTasks = new ArrayList<>();
        int index = 1;
        while (index <= numberOfPage) {
            List<WebElement> tasks = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-9")));
            for (int i = 1; i <= tasks.size(); i++) {
                int attemptCount = 2;
                int startIndex = 0;
                while (startIndex < attemptCount) {
                    try {
                        String taskName = driver.findElement(By.
                                xpath("//div[@class='row row-cols-1 row-cols-md-3 searchResultContent']//div[@name='task-card']["
                                        + i + "]//h5[@class='col-9 font-weight-bold text-left pr-0']")).getText();
                        overallTasks.add(taskName);
                        break;
                    } catch (StaleElementReferenceException e) {
                        e.printStackTrace();
                        startIndex++;
                    }
                }
            }
            driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
            index++;
        }
        return overallTasks;
    }

    public ArrayList<String> getListOfTasksOnCurrentPage() {
        ArrayList<String> tasks = new ArrayList<>();
        List<WebElement> taskCards = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-9")));
        for (int i = 0; i < taskCards.size(); i++) {
            String task = taskCards.get(i).getText();
            tasks.add(task);
        }
        return tasks;
    }

    //This method clicks on the Show More link until all task tiles are displayed on the page and Show More link becomes invisible
    public void clickOnShowMoreLinkUntilAllPagesAreLoaded() {
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
}
