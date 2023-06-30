package org.example.pages;

import org.example.pages.entity_search.SearchStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VolunteerInfoPage extends NavigationMenu {

    private SearchStrategy strategy;

    public VolunteerInfoPage(WebDriver driver) {
        super(driver);
        this.strategy = new SearchStrategy();

    }

    public List<String> search(String txt) {
        List<String> volunteerName = new ArrayList<>();
        this.strategy.searchEntity(txt);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card task-card h-100 mr-3']"));
        for (WebElement element : elements
        ) {
            volunteerName.add(element.findElement(By.tagName("a")).getText());
        }
        return volunteerName;
    }

    public boolean isVolunteerFound(List<String> volunteerNames, String searchTxt) {
        for (String volunteerName : volunteerNames
        ) {
            if (volunteerName.contains(searchTxt)) {
                return true;
            }
        } return false;
    }
}
