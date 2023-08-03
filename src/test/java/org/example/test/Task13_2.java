package org.example.test;

import org.example.common.CustomListener;
import org.example.entity.Gender;
import org.example.entity.Partner;
import org.example.pages.HomePage;
import org.example.pages.registration.SuccessRegistrationPage;
import org.example.setup.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_data.RandomData;

import static com.codeborne.selenide.Condition.exactText;
import static org.example.common.CustomLogger.logger;

@Listeners(CustomListener.class)
public class Task13_2 extends BaseTest {
    @Test(dataProvider = "testdata")
    public void createPartner(String email, String firstName, String lastName, Gender gender, String password,
                              String confirmPassword, String organization, String positionInOrganization) {
        logger.info("Data for new partner is generated");
        Partner partner = new Partner(email, firstName, lastName, gender, password, confirmPassword,
                organization, positionInOrganization );

        logger.info("Creating new partner...");
        SuccessRegistrationPage successPage = (SuccessRegistrationPage) new HomePage().
                goToRegistrationPage().
                goToPartnerCreationPage().
                fillInMandatoryFields(partner)
                .submit();

        successPage.getSuccessMessage().shouldHave(exactText("Congratulation! Your registration succeeded! " +
                "Message was sent to your email. Please confirm it."));

    }
    // Create object array with 4 rows and 8 columns: first parameter is row and second is column
    @DataProvider(name = "testdata")
    public Object[][] testDataFeed() {
        Object[][] partnerData = new Object[4][8];
        int rowCount = 4;
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][0] = RandomData.randomCorporateEmail();
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][1] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][2] = RandomData.randomFirstOrLastName(8);
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][3] = Gender.MALE;
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][4] = RandomData.randomPassword(8);
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][5] = partnerData[i][4];
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][6] = RandomData.randomString(10);
        }
        for (int i = 0; i < rowCount; i++) {
            partnerData[i][7] = RandomData.randomString(10);
        } return partnerData;
    }
}
