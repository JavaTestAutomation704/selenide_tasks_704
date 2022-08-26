package com.softserveinc.ita.rozetka.modals;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;

import java.util.List;

public class ShoppingCartModal {
    public boolean isShoppingCartEmpty() {
        return isVisible("//div[@data-testid='empty-cart']");
    }

    public ShoppingCartModal clear() {
        String cartItemActionButtonXpath = "//button[contains(@id, 'cartProductActions')]";
        if (isVisible(cartItemActionButtonXpath)) {
            List<SelenideElement> cartItems = $$x(cartItemActionButtonXpath)
                    .shouldBe(sizeGreaterThanOrEqual(1));
            for (SelenideElement item : cartItems) {
                item.shouldBe(visible).click();
                $x("//div[contains(@id, 'cartProductActions')]//button")
                        .shouldBe(visible)
                        .click();
            }
        }
        return this;
    }

    public Header close() {
        $x("//button[contains(@class, 'modal__close')]").click();
        return new Header();
    }

    public ShoppingCartModal remove(int productNumber) {
        $x(String.format("//button[@id='cartProductActions%s']", (productNumber - 1))).click();
        $x("//button[contains(@class, 'context-menu-actions__button')]").click();
        return this;
    }

    public ShoppingCartModal removeAllProducts() {
        $x("//div[@class='cart-header__remove']//button").click();
        $x("(//li[contains(@class,'popup-menu')])[1]//button").click();
        return this;
    }

    public CheckoutPage startCheckout() {
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public CartItem get(int cartItemNumber) {
        return new CartItem(cartItemNumber);
    }

    public long getTotalSum() {
        return getLong("//div[contains(@class,'sum-price')]");
    }
}