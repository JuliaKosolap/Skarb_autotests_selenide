package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.example.common.CustomLogger.logger;

public class LoginPage extends NavigationMenu{

    SelenideElement inputLogin = $("#login");
    SelenideElement inputPassword = $("#password");
    SelenideElement loginButton = $(byAttribute("name", "login-button"));

    @Step("Log in to the site")
    //The method verifies if user is already logged in. If not  - then tries to log in
    public HomePage login(String username, String password) {
            logger.info("Username is typed");
            inputLogin.sendKeys(username);

            logger.info("Password is typed");
            inputPassword.sendKeys(password);

            logger.info("Submit button is clicked");
            loginButton.click();
        return new HomePage();
    }


}
