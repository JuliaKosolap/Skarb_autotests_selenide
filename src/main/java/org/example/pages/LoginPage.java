package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.example.common.CustomLogger.logger;

public class LoginPage extends NavigationMenu{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "login")
    private WebElement inputLogin;

    @FindBy (id = "password")
    private WebElement inputPassword;

    @FindBy(name = "login-button")
    private WebElement loginButton;

    //The method verifies if user is already logged in. If not  - then tries to log in
    public HomePage login(String username, String password) {
            logger.info("Username is typed");
            inputLogin.sendKeys(username);

            logger.info("Password is typed");
            inputPassword.sendKeys(password);

            logger.info("Submit button is clicked");
            loginButton.click();
        return new HomePage(driver);
    }
}
