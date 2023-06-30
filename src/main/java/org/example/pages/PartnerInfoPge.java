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
    public boolean isPartnerFound(List<String> partnerNames, String searchTxt) {
        for (String partnerName : partnerNames
        ) {
            if (partnerName.contains(searchTxt)) {
                return true;
            }
        } return false;
    }
}
