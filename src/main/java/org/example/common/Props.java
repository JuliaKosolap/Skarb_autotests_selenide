package org.example.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
    //path to the config file
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static String siteUrl = "";
    public static String mailHogUrl = "";
    public static String volunteerLogin = "";
    public static String volunteerPassword = "";
    public static String volunteerFirstName = "";
    public static String volunteerLastName = "";
    public static String partnerLogin = "";
    public static String partnerPassword = "";
    public static String organizationLogin = "";
    public static String organizationPassword = "";

    public static void initProperties() throws IOException {
        //initializing Properties object
        Properties prop = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            //load properties file and read the data from it
            prop.load(fileInputStream);
            siteUrl = prop.getProperty("siteUrl");
            mailHogUrl = prop.getProperty("mailHogUrl");
            volunteerLogin = prop.getProperty("volunteerLogin");
            volunteerPassword = prop.getProperty("volunteerPassword");
            volunteerFirstName = prop.getProperty("volunteerFirstName");
            volunteerLastName = prop.getProperty("volunteerLastName");
            partnerLogin = prop.getProperty("partnerLogin");
            partnerPassword = prop.getProperty("partnerPassword");
            organizationLogin = prop.getProperty("organizationLogin");
            organizationPassword = prop.getProperty("organizationPassword");
        }
    }
    public static void resetProperties() {
        siteUrl = "";
        volunteerLogin = "";
        volunteerPassword = "";
        partnerLogin = "";
        partnerPassword = "";
        organizationLogin = "";
        organizationPassword = "";
    }
}
