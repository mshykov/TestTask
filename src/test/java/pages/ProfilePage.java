package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private String pageUrl = "https://my.rozetka.com.ua/";
    private SelenideElement content = $("[id=personal_information_content]");
    private SelenideElement confirmEmailButton = $(".btn-link-confirm-mail");
    private SelenideElement confirmationPopup = $("[name=app-message]");

    public ProfilePage open() {
        Selenide.open(pageUrl);
        return this;
    }

    public ProfilePage sendConfirmationEmail() {
        $(confirmEmailButton).click();
        $(confirmationPopup).waitUntil(Condition.appear, 10000);
        System.out.println($(confirmationPopup).getText());
        // Assert.assertEquals("Вам отправлено письмо для подтверждения эл.почты", $(confirmationPopup).getText());
        return this;
    }

    public ProfilePage shouldHaveConfirmation() {
        $(confirmationPopup).waitUntil(Condition.appear, 20000);
        System.out.println($(confirmationPopup).getText());
        // Assert.assertEquals("Адрес электронной почты подтвержден, спасибо", $(confirmationPopup).getText());
        return this;
    }
}
