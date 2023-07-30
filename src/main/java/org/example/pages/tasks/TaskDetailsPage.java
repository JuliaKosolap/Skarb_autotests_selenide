package org.example.pages.tasks;

import org.example.pages.NavigationMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
    @FindBy(xpath = "//div[@id='successMsgAlert']/h4")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@id='candidatesContainerParent']//i[@class='fa fa-chevron-down']")
    private WebElement potentialPerformersExpandButton;
    @FindBy(xpath = "//div[@id='respondedContainerParent']//i[@class='fa fa-chevron-down']")
    private WebElement respondedExpandButton;

    @FindBy(id = "taskRespondBtn")
    private WebElement respondButton;

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
    public String getSuccessMessage() {
        return successMessage.getText();
    }
    //Get the list of candidates to be assigned to the task
    //If needed volunteer is found then invite him to become performer of the task
    public boolean assignVolunteerToTask(String firstName, String lastName) {
        potentialPerformersExpandButton.click();
        List<WebElement> volunteerCards = driver.findElements(By.
                xpath("//div[@id='candidatesContainer']//div[@class='col-5']"));
        for (int i = 0; i < volunteerCards.size(); i++) {
            List<WebElement> volunteerNames = volunteerCards.get(i).findElements(By.tagName("span"));
            String volFirstName = volunteerNames.get(0).getText();
            String volLastName = volunteerNames.get(1).getText();
            if (volFirstName.equals(firstName) && volLastName.equals(lastName)) {
                List<WebElement> buttons = driver.findElements(By.xpath("//div[@id='candidatesContainerParent']//button"));
                buttons.get(i).click();
                driver.findElement(By.id("taskInvitePerformerModal")).findElement(By.tagName("a")).click();
                return true;
            }
        }
        return false;
    }

    //Get the list of the volunteers assigned to the task and find the current volunteer there
    //If not found - return false
    public boolean checkVolunteerAssigned(String firstName, String lastName) throws InterruptedException {
        respondedExpandButton.click();
        List<WebElement> volunteerCards = driver.findElements(By.
                xpath("//div[@id='respondedContainer']//div[@class='col-5']"));
        for (int i = 0; i < volunteerCards.size(); i++) {
            List<WebElement> volunteerNames = volunteerCards.get(i).findElements(By.tagName("span"));
            String volFirstName = volunteerNames.get(0).getText();
            String volLastName = volunteerNames.get(1).getText();
            if (volFirstName.equals(firstName) && volLastName.equals(lastName)) {
                return true;
            }
        }
        return false;
    }
    //Confirm to become performer of the task
    public TaskDetailsPage respondToBecomePerformer() {
        respondButton.click();
        WebElement taskRespondModal = driver.findElement(By.xpath("//div[@class='modal-footer']//a"));
        taskRespondModal.click();
        boolean isDisplayed = driver.findElement(By.xpath("//div[@class='modal-footer']//a")).isDisplayed();
        if (isDisplayed) {
            taskRespondModal.click();
        }
        return this;
    }
}
