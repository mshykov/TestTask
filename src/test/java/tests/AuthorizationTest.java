package tests;

import com.codeborne.selenide.Selenide;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import pages.ProfilePage;
import pages.RegistrationPage;
import testData.TestData;
import testHelpers.AuthorizationTestHelper;

public class AuthorizationTest {

    private RegistrationPage registrationPage = new RegistrationPage();
    private ProfilePage profilePage = new ProfilePage();

    @BeforeClass
    public static void startSuite() {
        timeout = 10000;
        startMaximized = true;
        browser = "chrome";
    }

    @Test
    public void CreateAndAuthorizationNewUser() {
        String userName = TestData.getUserFullName();
        String userEmail = TestData.getUserEmail();
        String password = TestData.getPassword();

        registrationPage.CreateNewUser(userName, userEmail, password);
        profilePage.sendConfirmationEmail();
        Selenide.open(AuthorizationTestHelper.getConfirmationLink(userEmail));
        profilePage.shouldHaveConfirmation();
    }

    @AfterClass
    public static void endSuite() {
        closeWebDriver();
    }
}
