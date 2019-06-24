package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class RegistrationPage {

    private String pageUrl = "https://my.rozetka.com.ua/signup/";
    private SelenideElement content = $(".signup");
    private SelenideElement nameField = $(byName("title"));
    private SelenideElement emailField = $(byName("login"));
    private SelenideElement passwordField = $(byName("password"));
    private SelenideElement submitButton = $(".btn-link-sign");

    public RegistrationPage open() {
        Selenide.open(pageUrl);
        return this;
    }

    public ProfilePage CreateNewUser(String name, String email, String password) {
        open();
        $(nameField).setValue(name);
        $(emailField).setValue(email);
        $(passwordField).setValue(password);
        $(submitButton).click();
        $(content).waitUntil(Condition.disappear, 20000);

        return new ProfilePage();
    }
}
