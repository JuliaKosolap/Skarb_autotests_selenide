package org.example.pages;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected List<WebElement> getVisibleElements(List<WebElement> elements) {
        //Need a guard clause here to ensure there are exactly two elements,
        //Or make the wait check for all elements more safely. Either way,
        //consider changing the method name to be clear about expectations
        List<WebElement> newList = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(
                ExpectedConditions.or(
                        //This should be done more safely, unless already guarded to expect 2 elements
                        ExpectedConditions.visibilityOfAllElements(elements)));
        System.out.println(elements);
        for (WebElement element : elements) {
            try {
                boolean displayed = element.isDisplayed();
                if (displayed) {
                    newList.add(element);
                    System.out.println(newList);
                    break;
                }
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        }
        return newList;
    }
}
