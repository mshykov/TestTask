package tests;

import com.codeborne.selenide.Selenide;
import dialogs.ShoppingCartDialog;
import org.junit.*;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import pages.HomePage;
import pages.ProfilePage;
import pages.RegistrationPage;
import pages.SearchResultPage;
import testData.TestData;
import testHelpers.AuthorizationTestHelper;

import java.io.IOException;
import java.util.Map;

public class AuthorizationTest {

    private HomePage homePage = new HomePage();
    private RegistrationPage registrationPage = new RegistrationPage();
    private ProfilePage profilePage = new ProfilePage();
    private SearchResultPage searchResultPage = new SearchResultPage();
    private ShoppingCartDialog shoppingCartDialog = new ShoppingCartDialog();

    private static String userName;
    private static String userEmail;
    private static String password;

    @BeforeClass
    public static void startSuite() throws IOException {
        timeout = 10000;
        startMaximized = true;
        browser = "chrome";

        userName = TestData.getUserFullName();
        userEmail = TestData.getUserEmail();
        password = TestData.getPassword();
    }

    @Test
    public void createAndAuthorizationNewUser() {
        homePage.openRegistrationPage();
        registrationPage.createNewUser(userName, userEmail, password);
        profilePage.sendConfirmationEmail();
        Selenide.open(AuthorizationTestHelper.getConfirmationLink(userEmail));
        profilePage.shouldHaveConfirmation();
    }

    @Test
    public void addItemToShoppingCart() {
        String productName = "Iphone X";
        Map<String, String> product;

        homePage.login(userEmail, password);
        homePage.searchForProduct(productName);
        product = searchResultPage.addProductToCart();
        homePage.openShoppingCartDialog();

        shoppingCartDialog.verifyGroupOfProducts(1);
        shoppingCartDialog.verifyAddedProduct(product);
    }

    @After
    public void logout() {
        homePage.logOut();
    }

    @AfterClass
    public static void endSuite() {
        closeWebDriver();
    }
}
