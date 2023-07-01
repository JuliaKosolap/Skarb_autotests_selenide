package org.example.test;

import common.CustomListener;
import common.CustomLogger;
import org.example.pages.*;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static common.CustomLogger.logger;
import java.util.List;
@Listeners(common.CustomListener.class)

public class Task11 extends BaseTest {
    private String username = "yavorsk@gmail.com";
    private String password = "PAssword@1";
    @Test
    public void searchForVolunteer() {
        VolunteerInfoPage volunteerInfoPage = (VolunteerInfoPage) (new HomePage(driver)).
                goToLoginPage().
                login(username, password).
                goToEntityInfoPage(EntityType.VOLUNTEERS);
        logger.info("Volunteers Info Page was opened");

        String searchTxt = "Marcus";
        logger.info("Search for the text: " + searchTxt);

        List<String> searchResult = volunteerInfoPage.search(searchTxt);
        logger.info("Text searched");

        Assert.assertTrue(volunteerInfoPage.isVolunteerFound(searchResult, searchTxt));
    }

    @Test
    public void searchForPartner() {
        PartnerInfoPge partnerInfoPge = (PartnerInfoPge) (new HomePage(driver)).
                goToLoginPage().
                login(username, password).
                goToEntityInfoPage(EntityType.PARTNERS);
        logger.info("Partner Info Page was opened");

        String searchTxt = "Thor";
        logger.info("Search for the text: " + searchTxt);

        List<String> searchResult = partnerInfoPge.search(searchTxt);
        logger.info("Text searched");

        Assert.assertTrue(partnerInfoPge.isPartnerFound(searchResult, searchTxt));
    }
}
