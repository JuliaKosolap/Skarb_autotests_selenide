package org.example.test;

import org.example.pages.*;
import org.example.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static common.CustomLogger.logger;

@Listeners(common.CustomListener.class)
public class OptionalTask_CalculateTasksOnLastPage extends BaseTest {

    @Test
    public void calculateNumberOfPartnerTasksOnLastPage() {
        PartnerTasksPage partnerTasksPage = (PartnerTasksPage) (new HomePage(driver)).
                goToTasksPage(EntityType.PARTNERS);
        logger.info("Partners Tasks Page was opened");

        List<String> taskNames = partnerTasksPage.getListOfTasksOnLastPage();
        logger.info("The list of tasks on the last page are collected");

        System.out.println("The tasks number on the last page is: " + taskNames.size());
        partnerTasksPage.printTheNamesOfTheTasks(taskNames);
    }
    @Test
    public void calculateNumberOfVolunteerTasksOnLastPage() {

        VolunteerTasksPage volunteerTasksPage = (VolunteerTasksPage) (new HomePage(driver)).
                goToTasksPage(EntityType.VOLUNTEERS);
        logger.info("Volunteers Tasks Page was opened");

        List<String> taskNames = volunteerTasksPage.getListOfTasksOnLastPage();
        logger.info("The list of tasks on the last page are collected");

        System.out.println("The tasks number on the last page is: " + taskNames.size());
        volunteerTasksPage.printTheNamesOfTheTasks(taskNames);
    }
}
