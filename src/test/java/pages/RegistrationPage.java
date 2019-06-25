package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class RegistrationPage {

    private SelenideElement content = $(".signup");
    private SelenideElement nameField = $(byName("title"));
    private SelenideElement emailField = $(byName("login"));
    private SelenideElement passwordField = $(byName("password"));
    private SelenideElement submitButton = $(".btn-link-sign");

    /**
     * Open Registration page by url
     *
     * @return instance of the registration page
     */
    public RegistrationPage open() {
        Selenide.open("https://my.rozetka.com.ua/signup/");

        return this;
    }

    /**
     * Create new user
     *
     * @param name     user name
     * @param email    user email or phone number
     * @param password password
     * @return instance of the profile page
     */
    public ProfilePage createNewUser(String name, String email, String password) {
        nameField.setValue(name);
        emailField.setValue(email);
        passwordField.setValue(password);
        submitButton.click();
        content.waitUntil(Condition.disappear, 20000);

        return new ProfilePage();
    }
}
