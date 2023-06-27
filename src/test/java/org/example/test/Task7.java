package org.example.test;

import entity.Gender;
import entity.Partner;
import org.example.pages.*;
import org.example.setup.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test_data.RandomData;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Task7 extends BaseTest {

    private String mailHogUrl = "http://185.149.40.46:8025/";
    private String firstName = RandomData.randomFirstOrLastName(8);
    private String lastName = RandomData.randomFirstOrLastName(8);
    private String corporateEmail = RandomData.randomCorporateEmail();
    private String password = RandomData.randomPassword(8);
    private String confirmPassword = password;
    private String organization = RandomData.randomString(10);
    private String positionInOrganization = RandomData.randomString(10);


    //This test creates a partner with valid values and verifies if success message appeared
    @Test
    public void createPartnerWithValidValues() {
        Partner partner = new Partner(firstName, lastName, corporateEmail, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage(driver).
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();
        Assert.assertTrue(successPage.isInitialized());

        //here we go to MailHog and confirm registration
        driver.get(mailHogUrl);

        (new MailHogPage(driver)).waitForNewMessageToAppear(partner.getEmail()).
                confirmRegistrationOfNewPartner(partner.getEmail());

        switchBetweenWindows();
        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage(driver);
        Assert.assertTrue(successRegistrationPage.isInitialized());

        Assert.assertEquals(successRegistrationPage.getMessage(), "Your email confirmed!");
    }
}
