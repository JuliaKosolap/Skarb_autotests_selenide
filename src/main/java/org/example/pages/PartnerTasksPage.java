package org.example.pages;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PartnerTasksPage extends NavigationMenu{

    @FindBy(css = "a[aria-label='Next']")
    private WebElement nextPageLink;
    public PartnerTasksPage(WebDriver driver) {
        super(driver);
    }

    //This method clicks on the Next link in the pagination section until the provided number of page is reached
    // and returns the names of the Tasks from these pages
    public ArrayList<String> getListOfTasksForGivenNumberOfPages(int numberOfPage) throws InterruptedException {
        ArrayList<String> overallTasks = new ArrayList<>();
        for (int i = 0; i < numberOfPage; i++) {
            Thread.sleep(30);
            List<WebElement> tasks = driver.findElements(By.className("col-9"));
            for (WebElement task: tasks
            ) {
                overallTasks.add(task.getText());
            }
            driver.findElement(By.cssSelector("a[aria-label='Next']")).click();

        }
        return overallTasks;
    }

    public ArrayList<String> getListOfTasksOnCurrentPage() {
        ArrayList<String> tasks = new ArrayList<>();
        List<WebElement> taskCards = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("task-card")));
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
