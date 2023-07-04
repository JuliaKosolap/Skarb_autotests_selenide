package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

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

    @FindBy(linkText = "Volunteers")
    private WebElement memberVolunteersMenuItem;

    @FindBy(linkText = "Organizations")
    private WebElement memberOrganizationsMenuItem;

    @FindBy(linkText = "Partners")
    private WebElement memberPartnersMenuItem;

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

    @FindBy(xpath = "//i[@class='fa fa-sign-in fa-3x text-dark-red']")
    private WebElement loginIcon;
    @FindBy(xpath = "//i[@class='fa fa-sign-out fa-3x text-dark-red']")
    private WebElement logoutIcon;

    public void expandAboutProjectMenu() {
        logger.info("'About Project' menu item is clicked");
        aboutProjectMenuItem.click();
    }

    public void expandMembersMenu() {
        logger.info("'Members' menu item is clicked");
        membersMenuItem.click();
    }

    public void expandTasksMenu() {
        logger.info("'Tasks' menu item is clicked");
        tasksMenuItem.click();
    }

    //This method checks what type of task is provided (Volunteer tasks or Partner tasks), then clicks on appropriate
    // link and creates the appropriate instance of a Page and returns it.
    public BasePage goToTasksPage(EntityType type) {
        logger.info("'Tasks' menu item is clicked");
        tasksMenuItem.click();

        if (type.equals(EntityType.VOLUNTEERS)) {
            logger.info("'For volunteers' menu sub-item is clicked");
            tasksForVolunteersMenuItem.click();

            return new VolunteerTasksPage(driver);
        } else {
            logger.info("'For Partners' menu sub-item is clicked");
            tasksForPartnersMenuItem.click();

            return new PartnerTasksPage(driver);
        }
    }

    public RegistrationPage goToRegistrationPage() {
        logger.info("'Registration' menu item is clicked");
        registrationMenuItem.click();

        return new RegistrationPage(driver);
    }

    public AboutUsPage goToAboutUsPage() {
        expandAboutProjectMenu();

        logger.info("'About Us' menu sub-item is clicked");
        aboutUsMenuItem.click();

        return new AboutUsPage(driver);
    }

    public NewsPage goToNewsPage() {
        expandAboutProjectMenu();

        logger.info("'News' menu sub-item is clicked");
        newsMenuItem.click();

        return new NewsPage(driver);
    }

    public RulesPage goToRulesPage() {
        expandAboutProjectMenu();

        logger.info("'Rules' menu sub-item is clicked");
        rulesMenuItem.click();

        return new RulesPage(driver);
    }

    public HelpPage goToHelpPage() {
        expandAboutProjectMenu();

        logger.info("'Help' menu sub-item is clicked");
        helpMenuItem.click();

        return new HelpPage(driver);
    }
    public BasePage goToEntityInfoPage(EntityType entityType) {
        expandMembersMenu();
        if (entityType.equals(EntityType.VOLUNTEERS)){
            logger.info("'Volunteers' menu sub-item is clicked");
            memberVolunteersMenuItem.click();

            return new VolunteerInfoPage(driver);
        } else if (entityType.equals(EntityType.PARTNERS)) {
            logger.info("'Partners' menu sub-item is clicked");
            memberPartnersMenuItem.click();

            return new PartnerInfoPge(driver);
        } else  {
            logger.info("'Organizations' menu sub-item is clicked");
            memberOrganizationsMenuItem.click();
            return new OrganizationInfoPage(driver);
        }
    }

    public LoginPage goToLoginPage() {
        try {
            logger.info("'Login' menu items is clicked");
            loginIcon.click();
        } catch (NoSuchElementException e) {
            logger.info("'Logout' menu items is clicked");
            logoutIcon.click();
        }
        return new LoginPage(driver);
    }

    public void selectLanguage(Language language) {
        logger.info("'Language' menu items is clicked");
        languageMenuItem.click();

       logger.info("Language is selected");
        switch (language) {
            case EN -> englishMenuItem.click();
            case UA -> ukrainianMenuItem.click();
            case RU -> russianMenuItem.click();
        }
    }
}
