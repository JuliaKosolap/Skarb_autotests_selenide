package org.example.test;

import org.example.common.CustomListener;
import org.example.common.Props;
import org.example.entity.Gender;
import org.example.entity.Partner;
import org.example.pages.HomePage;
import org.example.pages.registration.MailHogPage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.example.common.CustomLogger.logger;
@Listeners(CustomListener.class)
public class Task7 extends BaseTest {

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
        new HomePage().
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();

        //here we go to MailHog and confirm registration
        logger.info("MailHog site was opened");
        open(Props.mailHogUrl);

        logger.info("Waiting for new email to appear");
        (new MailHogPage()).confirmRegistrationOfNewPartner(partner.getEmail());

        logger.info("Switched to the main site");
        switchTo().window("Registration");

        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage();

        successRegistrationPage.getSuccessMessage().shouldHave(exactText( "Your email confirmed!"));
    }
}
