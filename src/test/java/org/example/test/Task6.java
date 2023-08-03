package org.example.test;

import org.example.common.CustomListener;
import org.example.entity.Gender;
import org.example.entity.Partner;
import org.example.pages.HomePage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import static com.codeborne.selenide.Condition.exactText;
import static org.example.common.CustomLogger.logger;

@Listeners(CustomListener.class)

public class Task6 extends BaseTest {
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
        logger.info("Data for new partner is generated");
        Partner partner = new Partner(email, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);

        logger.info("Partner is created");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage().
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();

        successPage.getSuccessMessage().
                shouldHave(exactText("Congratulation! Your registration succeeded! Message was sent to your email. Please confirm it."));

    }



}
