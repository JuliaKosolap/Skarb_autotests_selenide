package org.example.pages;

import org.example.pages.entity_search.SearchStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PartnerInfoPge extends NavigationMenu{
    private SearchStrategy strategy;
    public PartnerInfoPge(WebDriver driver) {
        super(driver);
        this.strategy = new SearchStrategy();
    }
    //This method do search on the Partner Info Page for provided string and returns names on the found cards
    public List<String> search(String txt) {
        List<String> partnerName = new ArrayList<>();
        this.strategy.searchEntity(txt);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card task-card h-100 mr-3']"));
        for (WebElement element : elements
        ) {
            partnerName.add(element.findElement(By.tagName("a")).getText());
        }
        return partnerName;
    }
    //This method compares the name on the found card with the provided string
    // and returns true if the name contains the provided text or false if not
    public boolean isPartnerFound(List<String> partnerNames, String searchTxt) {
        for (String partnerName : partnerNames
        ) {
            if (partnerName.contains(searchTxt)) {
                return true;
            }
        } return false;
    }
}
