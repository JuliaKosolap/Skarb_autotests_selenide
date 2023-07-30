package org.example.test;

import org.example.common.CustomListener;
import org.example.common.Props;
import org.example.entity.Task;
import org.example.pages.HomePage;
import org.example.pages.registration.MailHogPage;
import org.example.pages.tasks.TaskCategory;
import org.example.pages.tasks.TaskCreationPage;
import org.example.pages.tasks.TaskDetailsPage;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import java.text.ParseException;

import static org.example.common.CustomLogger.logger;
@Listeners(CustomListener.class)
public class FinalTask extends BaseTest {
    private String mailHogUrl = "http://185.149.40.46:8025/";

    @Test(dataProvider = "taskdata")
    public void assignTaskToVolunteer(String name, TaskCategory category, String deadline, String description,
                                      String result, String benefit, int savedMoney, int duration1, int duration2) throws InterruptedException {

        Task task = new Task(name, category, deadline, description, result, benefit, savedMoney, duration1, duration2);

        TaskDetailsPage taskDetailsPage = (TaskDetailsPage) new HomePage(driver).goToLoginPage().
                login(Props.organizationLogin, Props.organizationPassword).goToTaskCreationPage().
                fillMandatoryFields(task).clickCreateAndPublishTaskButton();

        Assert.assertEquals(taskDetailsPage.getSuccessMessage(), "New task has been successfully registered and published.");

        //assign task to volunteer and ensure that it was assigned
        Assert.assertTrue(taskDetailsPage.assignVolunteerToTask(Props.volunteerFirstName, Props.volunteerLastName));

        //go to MailHog and confirm invitation to become a task performer
            logger.info("MailHog site was opened");
            driver.get(mailHogUrl);

            logger.info("Waiting for new email to appear");
            (new MailHogPage(driver)).waitForNewMessageToAppear(Props.volunteerLogin).
                    confirmRegistrationOfNewPartner(Props.volunteerLogin);

            logger.info("Switched to the main site");
            switchBetweenWindows();
            String taskUrl = driver.getCurrentUrl();

            logger.info("Log in to site as volunteer");
            new TaskDetailsPage(driver).goToLoginPage().login(Props.volunteerLogin, Props.volunteerPassword);

            logger.info("Open the task");
            driver.get(taskUrl);

            logger.info("Confirm to be performer of the task");
            taskDetailsPage = new TaskDetailsPage(driver);
            taskDetailsPage.respondToBecomePerformer();

            logger.info("Verify that volunteer was assigned to the task");
            taskDetailsPage = new TaskDetailsPage(driver);
        Assert.assertTrue(taskDetailsPage.checkVolunteerAssigned(Props.volunteerFirstName, Props.volunteerLastName));

    }
    @DataProvider(name = "taskdata")
    public Object[][] taskDataFeed() throws ParseException {
        Object[][] taskData = new Object[1][9];
        int rowCount = 1;
        for (int i = 0; i < rowCount; i++) {
            taskData[i][0] = RandomData.randomString(8);
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][1] = TaskCategory.SOFT_DRINKS;
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][2] = TaskCreationPage.getFutureDateForTask(5);
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][3] = RandomData.randomString(20);
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][4] = RandomData.randomString(10);
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][5] = RandomData.randomString(10);
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][6] = 10;
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][7] = 20;
        }
        for (int i = 0; i < rowCount; i++) {
            taskData[i][8] = 10;
        }
        return taskData;
    }
}
