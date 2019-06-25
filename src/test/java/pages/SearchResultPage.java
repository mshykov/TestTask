package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage {

    private SelenideElement content = $(".container");
    private SelenideElement goods = content.$(byName("goods_list_container"));
    private ElementsCollection goodsList = goods.$$(".available");

    /**
     * Allows to add the first item on the page to the shopping cart
     *
     * @return product name and price added to the shopping cart
     */
    public Map<String, String> addProductToCart() {
        Map<String, String> product = new HashMap<String, String>();

        goods.waitUntil(Condition.appear, 10000);
        goodsList.shouldBe(CollectionCondition.sizeGreaterThan(0));

        SelenideElement firstItem = goodsList.first();
        String name = firstItem.$(".g-i-tile-i-title").getText();
        String price = firstItem.$(".g-price-uah").getText();
        String currency = firstItem.$(".g-price-uah-sign").getText();

        // replace in the product price: currency, '&thinsp;' and all whitespace
        price = price.replaceAll(currency, "");
        price = price.replace('\u2009', ' ');
        price = price.replaceAll("\\s+", "");

        product.put(name, price);
        firstItem.$(byName("buy_catalog")).click();

        return product;
    }
}
