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
    private String baseUrl = "https://skarb.foxminded.ua/";
    private String mailHogUrl = "http://185.149.40.46:8025/";
    private String firstName = RandomData.randomFirstOrLastName(8);
    private String lastName = RandomData.randomFirstOrLastName(8);
    private String corporateEmail = RandomData.randomCorporateEmail();
    private String password = RandomData.randomPassword(8);
    private String confirmPassword = password;
    private String organization = RandomData.randomString(10);
    private String positionInOrganization = RandomData.randomString(10);

    @BeforeMethod
    public void testSetUp() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    //This method opens the Registration page and then opens the Create Partner page
    private void goToPartnerCreationPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());
        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        registrationPage.goToPartnerCreationPage();
    }

    //This test creates a partner with valid values and verifies if success message appeared
    @Test
    public void createPartnerWithValidValues() {
        Partner partner = new Partner(corporateEmail, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);
        goToPartnerCreationPage();
        PartnerCreationPage partnerCreationPage = new PartnerCreationPage(driver);
        Assert.assertTrue(partnerCreationPage.isInitialized());
        fillInMandatoryFields(partner, partnerCreationPage);
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) partnerCreationPage.submit();
        Assert.assertTrue(successPage.isInitialized());
        confirmRegistration(partner);
        WebElement successMessage = driver.findElement(By.className("display-3"));
        Assert.assertEquals(successMessage.getText(), "Your email confirmed!");
    }

    //This method opens the MailHog service, waits for new record to appear in the list of registered users;
    //then opens this email, clicks on the Registration confirmation link and switches to the main site https://skarb.foxminded.ua/
    private void confirmRegistration(Partner partner) {
        driver.get(mailHogUrl);
        MailHogPage mailHogPage = new MailHogPage(driver);
        mailHogPage.waitForNewMessageToAppear(partner.getEmail());
        mailHogPage.confirmRegistrationOfNewPartner(partner.getEmail());
                String parentPage = driver.getWindowHandle();
                Set<String> s = driver.getWindowHandles();
                Iterator<String> I1 = s.iterator();
                while (I1.hasNext()) {
                    String child_window = I1.next();
                    if (!parentPage.equals(child_window)) {
                        driver.switchTo().window(child_window);
                    }
            }
        }

    //This method fills the mandatory fields for registration with provided values
    private void fillInMandatoryFields(Partner partner, PartnerCreationPage partnerCreationPage) {
        partnerCreationPage.enterFirstName(partner.getFirstName());
        partnerCreationPage.enterLastName(partner.getLastName());
        partnerCreationPage.enterEmail(partner.getEmail());
        partnerCreationPage.selectRadioButton(partner.getGender());
        partnerCreationPage.enterPassword(partner.getPassword());
        partnerCreationPage.enterConfirmPassword(partner.getConfirmPassword());
        partnerCreationPage.enterOrganization(partner.getOrganizationName());
        partnerCreationPage.enterPositionInOrganization(partner.getOrganizationPosition());
    }
}
