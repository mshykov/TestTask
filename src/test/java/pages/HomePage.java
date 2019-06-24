package pages;

import com.codeborne.selenide.Selenide;

public class HomePage {

    private String pageUrl = "https://rozetka.com.ua/";

    public HomePage open() {
        Selenide.open(pageUrl);
        return this;
    }
}