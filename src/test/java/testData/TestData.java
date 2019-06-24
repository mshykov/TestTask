package testData;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    private static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, false);
    }

    public static String getUserFullName() {
        String firstName = getRandomString(6);
        String lastName = getRandomString(10);

        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase() +
                " " + lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
    }

    public static String getPassword() {
        return "aB123456";
    }

    public static String getUserEmail() {
        return getRandomString(6).toLowerCase() + "@afteir.com";
    }
}
