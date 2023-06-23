package org.example.pages;

import entity.Gender;
import entity.Partner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnerCreationPage extends NavigationMenu {
    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "male")
    private WebElement maleRadioButton;
    @FindBy(id = "female")
    private WebElement femaleRadioButton;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "organizationName")
    private WebElement organization;

    @FindBy(id = "positionInOrganization")
    private WebElement positionInOrganization;

    @FindBy(name = "submit")
    private WebElement submitButton;

    public boolean isInitialized() {
        return firstName.isDisplayed();
    }

    public PartnerCreationPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        this.email.sendKeys(email);
    }

    public void selectRadioButton(Gender gender) {
        switch (gender) {
            case MALE -> maleRadioButton.click();
            case FEMALE -> femaleRadioButton.click();
        }
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
    }

    public void enterOrganization(String organization) {
        this.organization.sendKeys(organization);
    }

    public void enterPositionInOrganization(String position) {
        this.positionInOrganization.sendKeys(position);
    }

    //This method submits the partner creation form and returns success page in case of success registration or current page is case of any error
    public BasePage submit(){
        submitButton.click();
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Partner registration") || pageTitle.equals("Регистрация партнера") || pageTitle.equals("Реєстрація партнера")) {
            return this;
        } else {
            return new SuccessRegistrationPage(driver);
        }
    }
    //This method fills the mandatory fields for registration with provided values
    public void fillInMandatoryFields(Partner partner) {
        enterFirstName(partner.getFirstName());
        enterLastName(partner.getLastName());
        enterEmail(partner.getEmail());
        selectRadioButton(partner.getGender());
        enterPassword(partner.getPassword());
        enterConfirmPassword(partner.getConfirmPassword());
        enterOrganization(partner.getOrganizationName());
        enterPositionInOrganization(partner.getOrganizationPosition());
    }
}
