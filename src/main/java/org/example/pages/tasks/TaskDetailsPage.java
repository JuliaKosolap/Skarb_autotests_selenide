package org.example.pages.tasks;

import com.codeborne.selenide.SelenideElement;
import org.example.pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TaskDetailsPage extends BasePage {

    SelenideElement taskName = $(byAttribute("name", "task-name"));
    SelenideElement taskDescription = $(byAttribute("name", "task-description"));
    SelenideElement expectedResult = $(byAttribute("name", "expected-outcome"));
    SelenideElement volunteerBenefit = $(byAttribute("name", "volunteer-benefit"));
    SelenideElement category = $(byAttribute("name", "categories"));
    SelenideElement actualUntil = $(byAttribute("name", "task-deadline"));
    SelenideElement taskDuration = $(byAttribute("name", "task-duration"));
    SelenideElement authorFirstName = $(byXpath("//div[@name='task-author']//span[@class='text-muted'][1]"));
    SelenideElement authorLastName = $(byXpath("//div[@name='task-author']//span[@class='text-muted'][2]"));
    SelenideElement successMessage = $(byXpath("//div[@id='successMsgAlert']/h4"));
    SelenideElement potentialPerformersExpandButton = $(byXpath("//div[@id='candidatesContainerParent']//i[@class='fa fa-chevron-down']"));
    SelenideElement respondedExpandButton = $(byXpath("//div[@id='respondedContainerParent']//i[@class='fa fa-chevron-down']"));
    SelenideElement respondButton = $("#taskRespondBtn");

    public SelenideElement getTaskName() {
        return taskName;
    }

    public SelenideElement getTaskDescription() {
        return taskDescription;
    }

    public SelenideElement getExpectedResult() {
        return expectedResult;
    }

    public SelenideElement getVolunteerBenefit() {
        return volunteerBenefit;
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

    public SelenideElement getAuthorFirstName() {
        return authorFirstName;
    }
    public SelenideElement getAuthorLastName() {
        return authorLastName;
    }
    public SelenideElement getActualUntil() {
        return actualUntil;
    }
    public SelenideElement getTaskDuration() {
        return taskDuration;
    }
    public SelenideElement getSuccessMessage() {
        return successMessage;
    }
    //Get the list of candidates to be assigned to the task
    //If needed volunteer is found then invite him to become performer of the task
    public boolean assignVolunteerToTask(String firstName, String lastName) {
        potentialPerformersExpandButton.click();
        List<SelenideElement> volunteerCards = $$(byXpath(("//div[@id='candidatesContainer']//div[@class='col-5']")));
        for (int i = 0; i < volunteerCards.size(); i++) {
            List<SelenideElement> volunteerNames = volunteerCards.get(i).$$(byTagName("span"));
            String volFirstName = volunteerNames.get(0).getText();
            String volLastName = volunteerNames.get(1).getText();
            if (volFirstName.equals(firstName) && volLastName.equals(lastName)) {
                List<SelenideElement> buttons = $$(byXpath("//div[@id='candidatesContainerParent']//button"));
                buttons.get(i).click();
                $("#taskInvitePerformerModal").$(byTagName("a")).click();
                return true;
            }
        }
        return false;
    }

    //Get the list of the volunteers assigned to the task and find the current volunteer there
    //If not found - return false
//    public boolean checkVolunteerAssigned(String firstName, String lastName) throws InterruptedException {
//        respondedExpandButton.click();
//        List<WebElement> volunteerCards = driver.findElements(By.
//                xpath("//div[@id='respondedContainer']//div[@class='col-5']"));
//        for (int i = 0; i < volunteerCards.size(); i++) {
//            List<WebElement> volunteerNames = volunteerCards.get(i).findElements(By.tagName("span"));
//            String volFirstName = volunteerNames.get(0).getText();
//            String volLastName = volunteerNames.get(1).getText();
//            if (volFirstName.equals(firstName) && volLastName.equals(lastName)) {
//                return true;
//            }
//        }
//        return false;
//    }
    //Confirm to become performer of the task
//    public TaskDetailsPage respondToBecomePerformer() {
//        respondButton.click();
//        WebElement taskRespondModal = driver.findElement(By.xpath("//div[@class='modal-footer']//a"));
//        taskRespondModal.click();
//        boolean isDisplayed = driver.findElement(By.xpath("//div[@class='modal-footer']//a")).isDisplayed();
//        if (isDisplayed) {
//            taskRespondModal.click();
//        }
//        return this;
//    }
}
