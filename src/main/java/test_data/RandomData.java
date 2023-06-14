package test_data;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    //This method generates random alphabetic string of lowercase letters with the given length
    public static String randomString(int length) {
        String string = RandomStringUtils.randomAlphabetic(length).toLowerCase();
        return string;
    }
    //This method generates random first or last name with the given length that starts from the uppercase letter
    public static String randomFirstOrLastName(int length) {
        String firstName = RandomStringUtils.randomAlphabetic(1).toUpperCase() +
                RandomStringUtils.randomAlphabetic(length - 1).toLowerCase();
        return firstName;
    }


    //This method generates valid email
    public static String randomEmail() {
        String email = "TestEmail" + System.nanoTime() + "@gmail.com";
        return email;
    }

    //This method generates valid corporate email
    public static String randomCorporateEmail() {
        String email = RandomStringUtils.randomAlphanumeric(10) + "@skarb.ngo";
                return email;
    }
    //This method generates random email of invalid format and with length of 10 characters
    public static String invalidRandomEmail() {
        String email = RandomStringUtils.randomAlphanumeric(10);
        return email;
    }
    //This method generates random password with the given length that contains at least  2 upper case,
    // 2 lower case letters and 1 special character
    public static String randomPassword(int length) {
        String password = PasswordGeneratingUtil.generatePassword(length);
        return password;
    }

    //This method generates a valid phone number with length of 13 characters
    public static String randomPhoneNumber() {
       String phoneNumber = "+38098" + RandomStringUtils.randomNumeric(7);
       return phoneNumber;
    }
}
