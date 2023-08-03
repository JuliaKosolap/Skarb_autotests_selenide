package org.example.pages;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.example.pages.registration.RegistrationPage;
import org.example.pages.tasks.PartnerTasksPage;
import org.example.pages.tasks.VolunteerTasksPage;

import static org.example.common.CustomLogger.logger;

public class NavigationMenu {
    NavigationMenuLocators locators = new NavigationMenuLocators();

    public void expandAboutProjectMenu() {
        logger.info("'About Project' menu item is clicked");
        locators.aboutProjectMenuItem.click();
    }

    @Step("Expand Members Menu item")
    public void expandMembersMenu() {
        logger.info("'Members' menu item is clicked");
        locators.membersMenuItem.click();
    }

    @Step("Expand Tasks Menu item")
    public void expandTasksMenu() {
        logger.info("'Tasks' menu item is clicked");
        locators.tasksMenuItem.click();
    }
    @Step("Go to Tasks Page")
    //This method checks what type of task is provided (Volunteer tasks or Partner tasks), then clicks on appropriate
    // link and creates the appropriate instance of a Page and returns it.
    public BasePage goToTasksPage(EntityType type) {
        logger.info("'Tasks' menu item is clicked");
        locators.tasksMenuItem.click();

        if (type.equals(EntityType.VOLUNTEERS)) {
            logger.info("'For volunteers' menu sub-item is clicked");
            locators.tasksForVolunteersMenuItem.click();

            return new VolunteerTasksPage();
        } else {
            logger.info("'For Partners' menu sub-item is clicked");
            locators.tasksForPartnersMenuItem.click();

            return new PartnerTasksPage();
        }
    }
    @Step("Open registration page")
    public RegistrationPage goToRegistrationPage() {
        logger.info("'Registration' menu item is clicked");
        try {
            locators.registrationMenuItem.click();
        } catch (ElementNotFound e) {
            logger.info("Unable to click Registration menu item because user is logged");
            logger.info("Log out from the app");
            locators.logoutIcon.click();
            logger.info("'Registration' menu item is clicked");
            locators.registrationMenuItem.click();
        }
        return new RegistrationPage();
    }

    public AboutUsPage goToAboutUsPage() {
        expandAboutProjectMenu();

        logger.info("'About Us' menu sub-item is clicked");
        locators.aboutUsMenuItem.click();

        return new AboutUsPage();
    }

    @Step
    public NewsPage goToNewsPage() {
        expandAboutProjectMenu();

        logger.info("'News' menu sub-item is clicked");
        locators.newsMenuItem.click();

        return new NewsPage();
    }

    @Step("Go to Rules Page")
    public RulesPage goToRulesPage() {
        expandAboutProjectMenu();

        logger.info("'Rules' menu sub-item is clicked");
        locators.rulesMenuItem.click();

        return new RulesPage();
    }

    @Step("Go to Help Page")
    public HelpPage goToHelpPage() {
        expandAboutProjectMenu();

        logger.info("'Help' menu sub-item is clicked");
        locators.helpMenuItem.click();

        return new HelpPage();
    }

    @Step("Go to Login Page")
    public LoginPage goToLoginPage() {
        try {
            logger.info("'Login' menu items is clicked");
            locators.loginIcon.click();
        } catch (ElementNotFound e) {
            logger.info("'Logout' menu items is clicked");
            locators.logoutIcon.click();
        }
        return new LoginPage();
    }

    @Step("Select language")
    public void selectLanguage(Language language) {
        logger.info("'Language' menu items is clicked");
        locators.languageMenuItem.click();

       logger.info("Language is selected");
        switch (language) {
            case EN -> locators.englishMenuItem.click();
            case UA -> locators.ukrainianMenuItem.click();
            case RU -> locators.russianMenuItem.click();
        }
    }
}
