package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.PartnerTasksPage;
import org.example.pages.EntityType;
import org.example.setup.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class OptionalTask_CalculateTasksOnFivePages extends BaseTest {

    @Test
    public void calculateNumberOfTasksOnFivePages() throws InterruptedException {
        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) (new HomePage(driver)).goToTasksPage(EntityType.PARTNERS);

        ArrayList<String> listOfTasks = partnerTasksPage.getListOfTasksForGivenNumberOfPages(5);

        partnerTasksPage.printTheNamesOfTheTasks(listOfTasks);
        System.out.println("Overall number of tasks on 5 pages is: " + listOfTasks.size());

    }
}
