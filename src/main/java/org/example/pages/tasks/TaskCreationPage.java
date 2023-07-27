package org.example.pages.tasks;

import io.qameta.allure.Step;
import org.example.entity.Task;
import org.example.pages.BasePage;
import org.example.pages.NavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.example.common.CustomLogger.logger;

public class TaskCreationPage extends NavigationMenu {
    public TaskCreationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "name")
    private WebElement taskName;

    @FindBy(className = "filter-option")
    private WebElement taskCategoryDropdown;
    @FindBy(id = "bs-select-1-1")
    private WebElement graphicDesignDropdownItem;
    @FindBy(id = "bs-select-1-6")
    private WebElement softDrinksDropdownItem;
    @FindBy(id = "bs-select-1-2")
    private WebElement logoDesignDropdownItem;
    @FindBy(id = "deadline")
    private WebElement taskDeadline;

    @FindBy(id = "description")
    private WebElement taskDescription;

    @FindBy(id = "expectedOutcome")
    private WebElement taskResult;

    @FindBy(id = "benefit")
    private WebElement volunteerBenefit;

    @FindBy(id = "savedMoney")
    private WebElement savedMoney;

    @FindBy(name = "stages[0].duration")
    private WebElement stage1Duration;

    @FindBy(name = "stages[1].duration")
    private WebElement stage2Duration;

    @FindBy(xpath = "//button[@value='DRAFT']")
    private WebElement createTaskButton;

    public boolean isInitialized() {
        logger.info("Task creation page is initialized");
        return taskName.isDisplayed();
    }

    public TaskCreationPage enterTaskName(String taskName) {
        logger.info("Task name is typed");
        this.taskName.sendKeys(taskName);
        return this;
    }

    public TaskCreationPage selectCategory(TaskCategory category){
        logger.info("Category is selected");
        taskCategoryDropdown.click();
        switch (category) {
            case GRAPHIC_DESIGN -> graphicDesignDropdownItem.click();
            case SOFT_DRINKS -> softDrinksDropdownItem.click();
            case LOGO_DESIGN -> logoDesignDropdownItem.click();
        }
        return this;
    }
    public TaskCreationPage enterTaskDeadline(String date) {
        logger.info("Task Deadline is typed");
        this.taskDeadline.sendKeys(date);
        return this;
    }
    public TaskCreationPage enterTaskDescription(String description) {
        logger.info("Task description is typed");
        this.taskDescription.sendKeys(description);
        return this;
    }
    public TaskCreationPage enterTaskResult(String expectedResult) {
        logger.info("Expected task result is typed");
        this.taskResult.sendKeys(expectedResult);
        return this;
    }
    public TaskCreationPage enterVolunteerBenefit(String benefit) {
        logger.info("Volunteer benefit is typed");
        this.volunteerBenefit.sendKeys(benefit);
        return this;
    }
    public TaskCreationPage enterSavedMoney(int amount) {
        logger.info("Amount is typed");
        this.savedMoney.sendKeys(String.valueOf(amount));
        return this;
    }
    public TaskCreationPage enterStageDuration(int stage, int duration) {
        logger.info("Duration is typed");
        if (stage == 1) {
            this.stage1Duration.sendKeys(String.valueOf(duration));
        } else if (stage == 2) {
            this.stage2Duration.sendKeys(String.valueOf(duration));
        }
        return this;
    }
    @Step("Click Submit Button")
    public BasePage submit() {
        logger.info("Create Task button is clicked");
        createTaskButton.click();
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Task page") || pageTitle.equals("Страница задачи") || pageTitle.equals("Сторінка завдання")) {
            logger.info("New task details page was opened");
            return new TaskDetailsPage(driver);
        } else {
            logger.info("New task details page was not opened");
            return this;
        }
    }

    @Step("Fill Mandatory Fields")
    public TaskCreationPage fillMandatoryFields(Task task) {
        enterTaskName(task.getTaskName());
        selectCategory(task.getCategory());
        enterTaskDescription(task.getTaskDescription());
        enterTaskResult(task.getTaskResult());
        enterTaskDeadline(task.getTaskDeadline());
        enterVolunteerBenefit(task.getVolunteerBenefit());
        enterSavedMoney(task.getSavedMoney());
        enterStageDuration(1, task.getStage1Duration());
        enterStageDuration(2, task.getStage2Duration());
        return this;
    }
    //this method gets the number of the days, adds them to the current date and return needed date in the future for
    //a task deadline
    public static String getFutureDateForTask(int numberOfDays) {
        SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.YYYY");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numberOfDays);
        date = cal.getTime();
        String format = pattern.format(date);
        return format;
    }
}
