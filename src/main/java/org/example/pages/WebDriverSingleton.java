package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class WebDriverSingleton {
    private static WebDriver INSTANCE;

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ChromeDriver();
        }
        return INSTANCE;
    }
}
