package org.example.test;

import org.example.entity.Gender;
import org.example.entity.Partner;
import org.example.common.CustomListener;
import org.example.pages.*;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;
import static org.example.common.CustomLogger.logger;
@Listeners(CustomListener.class)
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
    public void createPartnerWithValidValues(){
        logger.info("Data for new partner was generated");
        Partner partner = new Partner(corporateEmail, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);

        logger.info("Partner was created");
        new HomePage(driver).
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();

        //here we go to MailHog and confirm registration
        logger.info("MailHog site was opened");
        driver.get(mailHogUrl);

        logger.info("Waiting for new email to appear");
        (new MailHogPage(driver)).waitForNewMessageToAppear(partner.getEmail()).
                confirmRegistrationOfNewPartner(partner.getEmail());

        logger.info("Switched to the main site");
        switchBetweenWindows();

        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage(driver);
        Assert.assertTrue(successRegistrationPage.isInitialized());

        Assert.assertEquals(successRegistrationPage.getMessage(), "Your email confirmed!");
    }
}
