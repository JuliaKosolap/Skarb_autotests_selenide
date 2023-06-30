package org.example.pages.entity_search;

import org.example.pages.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchStrategy implements IEntitySearchStrategy{
    private WebDriver driver = WebDriverSingleton.getInstance();
    private By searchField = By.className("form-control");
    private By searchButton = By.name("search-button");
    @Override
    public void searchEntity(String searchText) {
               driver.findElement(searchField).sendKeys(searchText);
               driver.findElement(searchButton).click();
    }
}
