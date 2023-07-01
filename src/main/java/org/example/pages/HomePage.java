package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends NavigationMenu {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "info_title")
    private WebElement header;

    public String getPageHeader() {
        return header.getText();
    }
    public boolean isInitialized() {
        return header.isDisplayed();
    }



}
