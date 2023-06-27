package org.example.test;

import org.example.pages.*;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OptionalTask_CalculateTasksOnLastPage extends BaseTest {

    @Test
    public void calculateNumberOfPartnerTasksOnLastPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) homePage.goToTasksPage(TasksType.PARTNERS);

        List<String> taskNames = partnerTasksPage.getListOfTasksOnLastPage();
        System.out.println("The tasks number on the last page is: " + taskNames.size());
        partnerTasksPage.printTheNamesOfTheTasks(taskNames);
    }
    @Test
    public void calculateNumberOfVolunteerTasksOnLastPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        VolunteerTasksPage volunteerTasksPage = (VolunteerTasksPage) homePage.goToTasksPage(TasksType.VOLUNTEERS);

        List<String> taskNames = volunteerTasksPage.getListOfTasksOnLastPage();
        System.out.println("The tasks number on the last page is: " + taskNames.size());
        volunteerTasksPage.printTheNamesOfTheTasks(taskNames);
    }
}
