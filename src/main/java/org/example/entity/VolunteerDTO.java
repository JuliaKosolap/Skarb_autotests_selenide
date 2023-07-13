package org.example.entity;

import lombok.Data;

@Data
public class VolunteerDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String sex;
    private String email;
    private String role;
    private String status;
    private String phoneNumber;
    private String skype;
    private String linkedIn;
    private String socialNetworks;
    private boolean isEmailConfirmed;
    private boolean owner;
    private String organizationId;
    private String positionInOrganization;
    private String volunteerDataId;
    private String lastActiveDate;

    private String createdDate;
    private String updatedDate;
    private String locale;
    private boolean phoneDisplayed;
    private boolean isSkypeDisplayed;
    private boolean isLinkedInDisplayed;
    private boolean isSocialDisplayed;
    private boolean notificationsToEmail;
    private boolean newsToEmail;
    private String avatarId;
    private boolean mfaEnabled;
    private int completedTaskNumber;

    public VolunteerDTO(String firstName, String lastName, String password, String sex, String email, String role,
                        String status, String phoneNumber, String skype, String linkedIn, String socialNetworks,
                        boolean emailConfirmed, boolean owner, String organizationId, String positionInOrganization,
                        String volunteerDataId, String lastActiveDate, String createdDate, String updatedDate,
                        String locale, boolean phoneDisplayed, boolean isSkypeDisplayed, boolean isLinkedInDisplayed,
                        boolean isSocialDisplayed, boolean notificationsToEmail, boolean newsToEmail, String avatarId,
                        boolean mfaEnabled, int completedTaskNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.role = role;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.skype = skype;
        this.linkedIn = linkedIn;
        this.socialNetworks = socialNetworks;
        this.isEmailConfirmed = emailConfirmed;
        this.owner = owner;
        this.organizationId = organizationId;
        this.positionInOrganization = positionInOrganization;
        this.volunteerDataId = volunteerDataId;
        this.lastActiveDate = lastActiveDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.locale = locale;
        this.phoneDisplayed = phoneDisplayed;
        this.isSkypeDisplayed = isSkypeDisplayed;
        this.isLinkedInDisplayed = isLinkedInDisplayed;
        this.isSocialDisplayed = isSocialDisplayed;
        this.notificationsToEmail = notificationsToEmail;
        this.newsToEmail = newsToEmail;
        this.avatarId = avatarId;
        this.mfaEnabled = mfaEnabled;
        this.completedTaskNumber = completedTaskNumber;
    }

}
