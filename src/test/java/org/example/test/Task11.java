package org.example.test;

import org.example.pages.*;
import org.example.pages.entity_search.SearchStrategy;
import org.example.pages.entity_search.SearchStrategyContext;
import org.example.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Task11 extends BaseTest {
    private String username = "yavorsk@gmail.com";
    private String password = "PAssword@1";
    @Test
    public void searchForVolunteer() {
        String searchTxt = "Marcus";

        VolunteerInfoPage volunteerInfoPage = (VolunteerInfoPage) (new HomePage(driver)).
                goToLoginPage().
                login(username, password).
                goToEntityInfoPage(EntityType.VOLUNTEERS);

        List<String> searchResult = volunteerInfoPage.search(searchTxt);
        Assert.assertTrue(volunteerInfoPage.isVolunteerFound(searchResult, searchTxt));
    }

    @Test
    public void searchForPartner() {
        String searchTxt = "Thor";

        PartnerInfoPge partnerInfoPge = (PartnerInfoPge) (new HomePage(driver)).
                goToLoginPage().
                login(username, password).
                goToEntityInfoPage(EntityType.PARTNERS);

        List<String> searchResult = partnerInfoPge.search(searchTxt);
        Assert.assertTrue(partnerInfoPge.isPartnerFound(searchResult, searchTxt));
    }
}
