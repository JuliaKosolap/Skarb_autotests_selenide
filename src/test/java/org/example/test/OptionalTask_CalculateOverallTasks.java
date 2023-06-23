package org.example.test;

import org.example.pages.BasePage;
import org.example.pages.HomePage;
import org.example.pages.PartnerTasksPage;
import org.example.pages.TasksType;
import org.example.setup.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class OptionalTask_CalculateOverallTasks extends BaseTest {

    //This method opens the Volunteer Tasks Page
    private PartnerTasksPage goToPartnersTasksPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        return (PartnerTasksPage) homePage.goToTasksPage(TasksType.PARTNERS);
    }
    @Test
    public void calculateNumberOfTasks() {
        PartnerTasksPage partnerTasksPage = goToPartnersTasksPage();
        partnerTasksPage.clickOnShowMoreLinkUntilAllPagesAreLoaded();
        ArrayList<String> listOfTasks = partnerTasksPage.getListOfTasksOnCurrentPage();
        int index = 1;
        for (String task : listOfTasks) {
            System.out.println("Task #" + (index) + ": " + task);
            index = index + 1;
        }
        System.out.println("Overall number of tasks is: " + listOfTasks.size());
        }

}
