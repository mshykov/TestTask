package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private SelenideElement content = $(byId("personal_information_content"));
    private SelenideElement needToConfirmPopup = $(".message-confirm-mail-wrap");
    private SelenideElement confirmEmailButton = $(".btn-link-confirm-mail");
    private SelenideElement confirmationPopup = $(byName("app-message"));

    /**
     * Open profile page by url
     *
     * @return instance of the profile page
     */
    public ProfilePage open() {
        Selenide.open("https://my.rozetka.com.ua/");

        return this;
    }

    /**
     * Send confirmation email and verify appeared message
     *
     * @return instance of the profile page
     */
    public ProfilePage sendConfirmationEmail() {
        confirmEmailButton.click();
        confirmationPopup.waitUntil(Condition.appear, 10000);
        confirmationPopup.shouldHave(Condition.text("Вам отправлено письмо для подтверждения эл.почты"));

        return this;
    }

    /**
     * Verify message about successfully email confirmation
     *
     * @return instance of the profile page
     */
    public ProfilePage shouldHaveConfirmation() {
        confirmationPopup.waitUntil(Condition.appear, 20000);
        confirmationPopup.shouldHave(Condition.text("Адрес электронной почты подтвержден, спасибо\n"));

        return this;
    }
}
