package org.example.test;

import entity.Gender;
import entity.Partner;
import org.example.pages.*;
import org.example.setup.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test_data.RandomData;

public class Task6 extends BaseTest {
    private String baseUrl = "https://skarb.foxminded.ua/";
    private String successPageUrl = "https://skarb.foxminded.ua/registration/result/success";
    private String firstName = RandomData.randomFirstOrLastName(8);
    private String lastName = RandomData.randomFirstOrLastName(8);
    private String email = RandomData.randomCorporateEmail();
    private String password = RandomData.randomPassword(8);
    private String confirmPassword = password;
    private String organization = RandomData.randomString(10);
    private String positionInOrganization = RandomData.randomString(10);

    //This test creates a partner with valid values and verifies if success message appeared
    @Test
    public void createPartnerWithValidValues() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        registrationPage.goToPartnerCreationPage();

        Partner partner = new Partner(email, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);

        PartnerCreationPage partnerCreationPage = new PartnerCreationPage(driver);
        Assert.assertTrue(partnerCreationPage.isInitialized());

        partnerCreationPage.fillInMandatoryFields(partner);
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) partnerCreationPage.submit();
        Assert.assertTrue(successPage.isInitialized());

        Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");

    }



}
