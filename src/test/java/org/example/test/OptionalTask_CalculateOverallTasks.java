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
public class OptionalTask_CalculateOverallTasks extends BaseTest {

    @Test
    public void calculateNumberOfTasks() throws InterruptedException {
        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) (new HomePage(driver)).goToTasksPage(EntityType.PARTNERS);
        logger.info("Partners Tasks Page was opened");

        int lastPageNumber = Integer.parseInt(partnerTasksPage.getTheLastPageNumber());
        logger.info("The last page number is: " + lastPageNumber);

        ArrayList<String> listOfTasks =  partnerTasksPage.getListOfTasksForGivenNumberOfPages(lastPageNumber);
        logger.info("The list of overall tasks for Partners are collected");

        partnerTasksPage.printTheNamesOfTheTasks(listOfTasks);
        System.out.println("Overall number of tasks is: " + listOfTasks.size());
        }

}
