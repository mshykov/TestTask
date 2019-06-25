package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dialogs.ShoppingCartDialog;
import dialogs.SignInDialog;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement content = $(".content.content_type_main");
    private SelenideElement header = $(".header");
    private SelenideElement userProfileLink = header.$(".header-topline__user-link.link-dashed");
    private ElementsCollection userDropDownList = header.$$(".header-dropdown__list-item");
    private SelenideElement searchField = header.$(byName("search"));
    private SelenideElement shoppingCartButton = header.$(".header-actions__item_type_cart");

    private SignInDialog signInDialog = new SignInDialog();

    /**
     * Open Rozetka home page: "https://rozetka.com.ua/"
     *
     * @return instance of the home page
     */
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/");

        return this;
    }

    /**
     * Open home page and sign in
     *
     * @param login    could be email or phone number
     * @param password user password
     * @return instance of the home page
     */
    public HomePage login(String login, String password) {
        logOut();
        userProfileLink.click();
        signInDialog.singIn(login, password);

        return this;
    }

    /**
     * Open home page and logout
     *
     * @return instance of the home page
     */
    public HomePage logOut() {
        open();
        if (!userProfileLink.getText().equalsIgnoreCase("войдите в личный кабинет")) {
            userProfileLink.hover();
            userDropDownList.find(Condition.text("Выход")).click();
            userProfileLink.waitUntil(Condition.text("войдите в личный кабинет"), 10000);
        }

        return this;
    }

    /**
     * Open home page, open sing in dialog and open registration page
     *
     * @return instance of the registration page
     */
    public RegistrationPage openRegistrationPage() {
        logOut();
        userProfileLink.click();
        signInDialog.clickRegistration();

        return new RegistrationPage();
    }

    /**
     * Open home page and search for product
     *
     * @param name product for searching
     * @return instance of the search result page
     */
    public SearchResultPage searchForProduct(String name) {
        open();
        searchField.setValue(name).pressEnter();
        content.waitUntil(Condition.disappear, 10000);

        return new SearchResultPage();
    }

    /**
     * Open home page and open shopping cart dialog
     *
     * @return instance of the shopping cart page
     */
    public ShoppingCartDialog openShoppingCartDialog() {
        open();
        shoppingCartButton.click();

        return new ShoppingCartDialog();
    }
}