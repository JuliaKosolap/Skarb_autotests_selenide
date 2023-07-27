package org.example.pages;

import io.qameta.allure.Step;
import org.example.pages.tasks.TaskCreationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

public class HomePage extends NavigationMenu {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "info_title")
    private WebElement header;
    @FindBy(name = "tasks-button")
    private WebElement createTaskButton;


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
        return new TaskCreationPage(driver);
    }


}
