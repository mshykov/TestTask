package dialogs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.HomePage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byAttribute;

public class SignInDialog {

    private SelenideElement content = $(".auth-modal__inner");
    private SelenideElement registerLink = content.$(".auth-modal__register-link");
    private SelenideElement loginField = content.$(byAttribute("type", "email"));
    private SelenideElement passwordField = content.$(byAttribute("type", "password"));
    private SelenideElement signInButton = content.$(".button-inner");

    /**
     * Click on registration link on the 'Sing In' dialog
     *
     * @return instance of the registration page
     */
    public RegistrationPage clickRegistration() {
        content.waitUntil(Condition.appear, 10000);
        registerLink.click();
        content.waitUntil(Condition.disappear, 10000);

        return new RegistrationPage();
    }

    /**
     * Sing In
     *
     * @param login    user email or phone number
     * @param password user password
     * @return instance of the home page
     */
    public HomePage singIn(String login, String password) {
        content.waitUntil(Condition.appear, 10000);
        loginField.setValue(login);
        passwordField.setValue(password);
        signInButton.click();
        content.waitUntil(Condition.disappear, 10000);

        return new HomePage();
    }
}
