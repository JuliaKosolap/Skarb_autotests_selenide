package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.PartnerTasksPage;
import org.example.pages.EntityType;
import org.example.setup.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class OptionalTask_CalculateOverallTasks extends BaseTest {

    @Test
    public void calculateNumberOfTasks() throws InterruptedException {
        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) (new HomePage(driver)).goToTasksPage(EntityType.PARTNERS);

        int lastPageNumber = Integer.parseInt(partnerTasksPage.getTheLastPageNumber());
        ArrayList<String> listOfTasks =  partnerTasksPage.getListOfTasksForGivenNumberOfPages(lastPageNumber);

        partnerTasksPage.printTheNamesOfTheTasks(listOfTasks);
        System.out.println("Overall number of tasks is: " + listOfTasks.size());
        }

}
