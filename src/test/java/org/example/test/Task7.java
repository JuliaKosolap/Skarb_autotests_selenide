package org.example.test;

import entity.Gender;
import entity.Partner;
import org.example.pages.*;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;
import static common.CustomLogger.logger;
@Listeners(common.CustomListener.class)
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
    public void createPartnerWithValidValues() throws InterruptedException {
        Partner partner = new Partner(corporateEmail, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);
        logger.info("Data for new partner was generated");

        new HomePage(driver).
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();
        logger.info("Partner was created");

        //here we go to MailHog and confirm registration
        driver.get(mailHogUrl);
        logger.info("MailHog site was opened");

        (new MailHogPage(driver)).waitForNewMessageToAppear(partner.getEmail()).
                confirmRegistrationOfNewPartner(partner.getEmail());
        logger.info("Waiting for new email to appear");

        switchBetweenWindows();
        logger.info("Switched to the main site");

        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage(driver);
        Assert.assertTrue(successRegistrationPage.isInitialized());

        Assert.assertEquals(successRegistrationPage.getMessage(), "Your email confirmed!");
    }
}
