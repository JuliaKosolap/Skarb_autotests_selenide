package org.example.test;

import org.example.common.CustomListener;
import org.example.common.Props;
import org.example.entity.Gender;
import org.example.entity.Organization;
import org.example.entity.Task;
import org.example.pages.HomePage;
import org.example.pages.registration.MailHogPage;
import org.example.pages.registration.SuccessRegistrationPage;
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
public class Task13_3 extends BaseTest {
    private String mailHogUrl = "http://185.149.40.46:8025/";

    @Test(dataProvider = "organizationdata")
    public void registerOrganization(String corporateEmail, String firstName, String lastName, Gender gender,
                                     String password, String confirmPassword,
                                     String organization, String positionInOrganization) {
        logger.info("Data for new organization is generated");
        Organization newOrganization = new Organization(corporateEmail, firstName, lastName, gender, password, confirmPassword,
                organization, positionInOrganization );

        logger.info("Creating new organization...");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage(driver).
                goToRegistrationPage().
                goToOrganizationRegistrationPage().
                fillInMandatoryFields(newOrganization)
                .submit();

        Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");

        //here we go to MailHog and confirm registration
        logger.info("MailHog site was opened");
        driver.get(mailHogUrl);

        logger.info("Waiting for new email to appear");
        (new MailHogPage(driver)).waitForNewMessageToAppear(newOrganization.getEmail()).
                confirmRegistrationOfNewPartner(newOrganization.getEmail());

        logger.info("Switched to the main site");
        switchBetweenWindows();

        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage(driver);
        Assert.assertTrue(successRegistrationPage.isInitialized());
        Assert.assertEquals(successRegistrationPage.getMessage(), "Your email confirmed!");
    }
    @Test(dataProvider = "taskdata")
    public void createTask(String name, TaskCategory category, String deadline, String description, String result,
                           String benefit, int savedMoney, int duration1, int duration2) {

        Task task = new Task(name, category, deadline, description, result, benefit, savedMoney, duration1, duration2);

        TaskDetailsPage taskDetailsPage = (TaskDetailsPage) new HomePage(driver).
                goToLoginPage().
                login(Props.organizationLogin,
                Props.organizationPassword).
                goToTaskCreationPage().
                fillMandatoryFields(task).
                submit();

        Assert.assertEquals(taskDetailsPage.getTaskName(), task.getTaskName());
        Assert.assertEquals(taskDetailsPage.getTaskDescription(), task.getTaskDescription());
        Assert.assertEquals(taskDetailsPage.getExpectedResult(), task.getTaskResult());
        Assert.assertEquals(taskDetailsPage.getVolunteerBenefit(), task.getVolunteerBenefit());
        Assert.assertEquals(taskDetailsPage.getTaskCategory(), task.getCategory());
        Assert.assertEquals(taskDetailsPage.getActualUntil(), task.getTaskDeadline());
        Assert.assertEquals(taskDetailsPage.getTaskDuration(), "Up to month");
    }
    @DataProvider(name = "organizationdata")
    // Create object array with 1 row and 8 columns: first parameter is row and second is column
    public Object[][] organizationDataFeed() {
        Object[][] orgData = new Object[1][8];
        int rowCount = 1;
        for (int i = 0; i < rowCount; i++) {
            orgData[i][0] = RandomData.randomCorporateEmail();
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][1] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][2] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][3] = Gender.MALE;
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][4] = RandomData.randomPassword(8);
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][5] = orgData[i][4];
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][6] = RandomData.randomString(10);
        }
        for (int i = 0; i < rowCount; i++) {
            orgData[i][7] = RandomData.randomString(10);
        }

        return orgData;
    }

    // Create object array with 4 rows and 8 columns: first parameter is row and second is column
    @DataProvider(name = "taskdata")
    public Object[][] taskDataFeed() throws ParseException {
        Object[][] taskData = new Object[3][9];
        int rowCount = 3;
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
