package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class NavigationMenuLocators {
    SelenideElement homePageMenuItem = $(byClassName("navbar-brand"));
    SelenideElement aboutProjectMenuItem = $("#aboutDropdown");
    SelenideElement membersMenuItem = $("#membersDropdown");
    SelenideElement tasksMenuItem = $("#tasksDropdown");
    SelenideElement languageMenuItem = $("#langDropdown");
    SelenideElement tasksForPartnersMenuItem = $(byLinkText("For partners"));
    SelenideElement tasksForVolunteersMenuItem = $(byLinkText("For volunteers"));
    SelenideElement memberVolunteersMenuItem = $(byLinkText("Volunteers"));
    SelenideElement memberOrganizationsMenuItem = $(byLinkText("Organizations"));
    SelenideElement memberPartnersMenuItem = $(byLinkText("memberPartnersMenuItem"));
    SelenideElement aboutUsMenuItem = $(byXpath("//div[@class='dropdown-menu show']//a[@href='/static/about']"));
    SelenideElement newsMenuItem = $(byXpath("//div[@class='dropdown-menu show']//a[@href='/news']"));
    SelenideElement rulesMenuItem = $(byXpath("//div[@class='dropdown-menu show']//a[@href='/static/rules']"));
    SelenideElement helpMenuItem = $(byXpath("//div[@class='dropdown-menu show']//a[@href='/static/help']"));
    SelenideElement englishMenuItem = $(byXpath("//a[@data-lang='en']"));
    SelenideElement russianMenuItem = $(byXpath("//a[@data-lang='ru']"));
    SelenideElement ukrainianMenuItem = $(byXpath("//a[@data-lang='uk']"));
    SelenideElement registrationMenuItem = $(byClassName("fa-user-plus"));
    SelenideElement loginIcon = $(byXpath("//i[@class='fa fa-sign-in fa-3x text-dark-red']"));
    SelenideElement logoutIcon = $(byXpath("//i[@class='fa fa-sign-out fa-3x text-dark-red']"));

}
