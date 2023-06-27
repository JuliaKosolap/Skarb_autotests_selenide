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

    @Test
    public void calculateNumberOfTasks() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) homePage.goToTasksPage(TasksType.PARTNERS);

        int lastPageNumber = Integer.parseInt(partnerTasksPage.getTheLastPageNumber());
        ArrayList<String> listOfTasks =  partnerTasksPage.getListOfTasksForGivenNumberOfPages(lastPageNumber);

        partnerTasksPage.printTheNamesOfTheTasks(listOfTasks);
        System.out.println("Overall number of tasks is: " + listOfTasks.size());
        }

}
