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
   //This method do search on the Volunteers Info Page for provided string and returns names on the found cards
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

    //This method compares the name on the found card with the provided string
    // and returns true if the name contains the provided text or false if not
    public boolean isVolunteerFound(List<String> volunteerNames, String searchTxt) {
        for (String volunteerName : volunteerNames
        ) {
            if (volunteerName.contains(searchTxt)) {
                return true;
            }
        } return false;
    }
}
