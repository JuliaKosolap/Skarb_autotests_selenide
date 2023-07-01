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
        Partner partner = new Partner(email, firstName, lastName, Gender.FEMALE, password, confirmPassword, organization, positionInOrganization);
        logger.info("Data for new partner was generated");

        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage(driver).
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();
        logger.info("Partner was created");

        Assert.assertEquals(successPage.getMessage(), "Congratulation! Your registration succeeded! Message was sent to your email. " +
                "Please confirm it.");

    }



}
