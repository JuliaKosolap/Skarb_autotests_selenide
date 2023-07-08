package org.example.pages.tasks;

import jdk.jfr.Category;
import org.example.pages.NavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.openqa.selenium.support.FindBy;

import static org.example.common.CustomLogger.logger;

public class TaskDetailsPage extends NavigationMenu {
    public TaskDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "task-name")
    private WebElement taskName;

    @FindBy(name = "task-description")
    private WebElement taskDescription;

    @FindBy(name = "expected-outcome")
    private WebElement expectedResult;

    @FindBy(name = "volunteer-benefit")
    private WebElement volunteerBenefit;

    @FindBy(name = "categories")
    private WebElement category;

    @FindBy(name = "task-deadline")
    private WebElement actualUntil;

    @FindBy(name = "task-duration")
    private WebElement taskDuration;

    @FindBy(xpath = "//div[@name='task-author']//span[@class='text-muted'][1]")
    private WebElement authorFirstName;

    @FindBy(xpath = "//div[@name='task-author']//span[@class='text-muted'][2]")
    private WebElement authorLastName;

    public String getTaskName() {
        return taskName.getText();
    }

    public String getTaskDescription() {
        return taskDescription.getText();
    }

    public String getExpectedResult() {
        return expectedResult.getText();
    }

    public String getVolunteerBenefit() {
        return volunteerBenefit.getText();
    }

    public TaskCategory getTaskCategory() {
        String categoryText = category.getText();
        if (categoryText.equals("Soft drinks")) {
            return TaskCategory.SOFT_DRINKS;
        } else if (categoryText.equals("Graphic design")) {
            return TaskCategory.GRAPHIC_DESIGN;
        } else if (categoryText.equals("Logo design")) {
            return TaskCategory.LOGO_DESIGN;
        } return null;
    }

    public String getAuthorFirstName() {
        return authorFirstName.getText();
    }
    public String getAuthorLastName() {
        return authorLastName.getText();
    }
    public String getActualUntil() {
        return actualUntil.getText();
    }
    public String getTaskDuration() {
        return taskDuration.getText();
    }
}
