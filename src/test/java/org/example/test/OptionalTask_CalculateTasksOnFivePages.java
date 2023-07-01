package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.PartnerTasksPage;
import org.example.pages.EntityType;
import org.example.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static common.CustomLogger.logger;

import java.util.ArrayList;
@Listeners(common.CustomListener.class)
public class OptionalTask_CalculateTasksOnFivePages extends BaseTest {

    @Test
    public void calculateNumberOfTasksOnFivePages() throws InterruptedException {
        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) (new HomePage(driver)).goToTasksPage(EntityType.PARTNERS);
        logger.info("Partners Tasks Page was opened");

        ArrayList<String> listOfTasks = partnerTasksPage.getListOfTasksForGivenNumberOfPages(5);
        logger.info("The list of tasks for Partners on 5 pages are collected");

        partnerTasksPage.printTheNamesOfTheTasks(listOfTasks);
        System.out.println("Overall number of tasks on 5 pages is: " + listOfTasks.size());

    }
}
