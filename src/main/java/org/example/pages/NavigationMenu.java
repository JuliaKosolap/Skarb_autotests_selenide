package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationMenu extends BasePage {
    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "navbar-brand")
    private WebElement homePageMenuItem;

    @FindBy(id = "aboutDropdown")
    private WebElement aboutProjectMenuItem;

    @FindBy(id = "membersDropdown")
    private WebElement membersMenuItem;

    @FindBy(id = "tasksDropdown")
    private WebElement tasksMenuItem;

    @FindBy(id = "langDropdown")
    private WebElement languageMenuItem;

    @FindBy(linkText = "For partners")
    private WebElement tasksForPartnersMenuItem;
    @FindBy(linkText = "For volunteers")
    private WebElement tasksForVolunteersMenuItem;

    @FindBy(xpath = "//div[@class='dropdown-menu show']//a[@href='/static/about']")
    private WebElement aboutUsMenuItem;

    @FindBy(xpath = "//div[@class='dropdown-menu show']//a[@href='/news']")
    private WebElement newsMenuItem;

    @FindBy(xpath = "//div[@class='dropdown-menu show']//a[@href='/static/rules'] ")
    private WebElement rulesMenuItem;

    @FindBy(xpath = "//div[@class='dropdown-menu show']//a[@href='/static/help']")
    private WebElement helpMenuItem;

    @FindBy(xpath = "//a[@data-lang='en']")
    private WebElement englishMenuItem;

    @FindBy(xpath = "//a[@data-lang='ru']")
    private WebElement russianMenuItem;

    @FindBy(xpath = "//a[@data-lang='uk']")
    private WebElement ukrainianMenuItem;

    @FindBy(className = "fa-user-plus")
    private WebElement registrationMenuItem;

    public void expandAboutProjectMenu() {
        aboutProjectMenuItem.click();
    }

    public void expandMembersMenu() {
        membersMenuItem.click();
    }

    public void expandTasksMenu() {
        tasksMenuItem.click();
    }

    //This method checks what type of task is provided (Volunteer tasks or Partner tasks), then clicks on appropriate
    // link and creates the appropriate instance of a Page and returns it.
    public BasePage goToTasksPage(TasksType type) {
        tasksMenuItem.click();
        if (type.equals(TasksType.VOLUNTEERS)) {
            tasksForVolunteersMenuItem.click();
            return new VolunteerTasksPage(driver);
        } else {
            tasksForPartnersMenuItem.click();
            return new PartnerTasksPage(driver);
        }
    }

    public RegistrationPage goToRegistrationPage() {
        registrationMenuItem.click();
        return new RegistrationPage(driver);
    }

    public AboutUsPage goToAboutUsPage() {
        aboutProjectMenuItem.click();
        aboutUsMenuItem.click();
        return new AboutUsPage(driver);
    }

    public NewsPage goToNewsPage() {
        aboutProjectMenuItem.click();
        newsMenuItem.click();
        return new NewsPage(driver);
    }

    public RulesPage goToRulesPage() {
        aboutProjectMenuItem.click();
        rulesMenuItem.click();
        return new RulesPage(driver);
    }

    public HelpPage goToHelpPage() {
        aboutProjectMenuItem.click();
        helpMenuItem.click();
        return new HelpPage(driver);
    }

    public void selectLanguage(Language language) {
        languageMenuItem.click();
        switch (language) {
            case EN -> englishMenuItem.click();
            case UA -> ukrainianMenuItem.click();
            case RU -> russianMenuItem.click();
        }
    }
}
