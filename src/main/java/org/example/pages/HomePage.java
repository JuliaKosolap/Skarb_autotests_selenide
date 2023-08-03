package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.pages.tasks.TaskCreationPage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class HomePage extends NavigationMenu {

    SelenideElement header  = $(byAttribute("class", "title info_title title--red"));
    SelenideElement createTaskButton = $(byAttribute("name", "tasks-button"));

    public String getPageHeader() {
        logger.info("Page header is returned");
        return header.getText();
    }
    public boolean isInitialized() {
        logger.info("Home Page is initialized");
        return header.isDisplayed();
    }
    @Step("Go to Task Creation Page")
    public TaskCreationPage goToTaskCreationPage() {
        createTaskButton.click();
        return new TaskCreationPage();
    }


}
