package org.example.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.example.common.Props.initProperties;
import static org.example.common.Props.resetProperties;


public class BaseTest {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws IOException {
        Configuration.timeout = 30000;
        Configuration.browser = browser;
        System.out.println("Current browser: " + browser);
        initProperties();
    }
    @BeforeTest
    public void setUp2() {
        open("https://skarb.foxminded.ua/");
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        resetProperties();
        Selenide.closeWebDriver();
    }
}
