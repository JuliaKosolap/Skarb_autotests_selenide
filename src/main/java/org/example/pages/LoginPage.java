package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
            inputLogin.sendKeys(username);
            inputPassword.sendKeys(password);
            loginButton.click();
        return new HomePage(driver);
    }
}
