package org.example.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Data

public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private String status;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "skype")
    private String skype;
    @Column(name = "linked_in")
    private String linkedIn;
    @Column(name = "social_networks")
    private String socialNetworks;
    @Column(name = "email_confirmed")
    private boolean isEmailConfirmed;
    @Column(name = "owner")
    private boolean owner;
    @Column(name = "organization_id")
    private String organizationId;
    @Column(name = "position_in_organization")
    private String positionInOrganization;
    @Column(name = "volunteer_data_id")
    private String volunteerDataId;
    @Column(name = "last_active_date")
    private String lastActiveDate;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "updated_date")
    private String updatedDate;
    @Column(name = "locale")
    private String locale;
    @Column(name = "phone_displayed")
    private boolean phoneDisplayed;
    @Column(name = "skype_displayed")
    private boolean isSkypeDisplayed;
    @Column(name = "linked_in_displayed")
    private boolean isLinkedInDisplayed;
    @Column(name = "social_displayed")
    private boolean isSocialDisplayed;
    @Column(name = "notifications_to_email")
    private boolean notificationsToEmail;
    @Column(name = "news_to_email")
    private boolean newsToEmail;
    @Column(name = "avatar_id")
    private String avatarId;
    @Column(name = "mfa_enabled")
    private boolean mfaEnabled;
    @Column(name = "completed_task_count")
    private int completedTaskNumber;

    public Volunteer(String firstName, String lastName, String password, String sex, String email, String role,
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
