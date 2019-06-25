package dialogs;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartDialog {

    private SelenideElement content = $(".cart-modal__inner");
    private SelenideElement cartItems = content.$(".cart-modal__list");
    private ElementsCollection cartItem = cartItems.$$(".cart-modal__item");

    /**
     * Verify count of different product groups in the cart
     *
     * @param groupCount count of different product groups in the cart
     * @return instance of the shopping cart dialog
     */
    public ShoppingCartDialog verifyGroupOfProducts(int groupCount) {
        cartItem.shouldHaveSize(groupCount);

        return this;
    }

    /**
     * Verify that product with current name and price is added to the cart
     *
     * @param product product name and product price
     * @return instance of the shopping cart dialog
     */
    public ShoppingCartDialog verifyAddedProduct(Map<String, String> product) {
        SelenideElement firstItem = cartItem.first();
        String productName = firstItem.$(".cart-modal__title").getText();
        String productPrice = firstItem.$(".cart-modal__coast-digits").getText();
        productPrice = productPrice.replaceAll("\\s+", "");

        Assert.assertEquals(product.get(productName), productPrice);

        return this;
    }
}
