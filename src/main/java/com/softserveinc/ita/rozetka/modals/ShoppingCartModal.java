package com.softserveinc.ita.rozetka.modals;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;

import static utils.WebElementUtil.getText;
import java.util.List;

public class ShoppingCartModal {
    public boolean isEmpty() {
        return isVisible("//div[@data-testid='empty-cart']");
    }

    public ShoppingCartModal clear() {
        String cartItemActionButtonXpath = "//button[contains(@id, 'cartProductActions')]";
        if (isVisible(cartItemActionButtonXpath)) {
            List<SelenideElement> cartItems = $$x(cartItemActionButtonXpath)
                    .shouldBe(sizeGreaterThanOrEqual(1));
            for (SelenideElement item : cartItems) {
                item.click();
                $x("//div[contains(@id, 'cartProductActions')]//button").click();
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

    public boolean isRemoveAllProductsButtonVisible() {
        return isVisible("//div[@class='cart-header__remove']//button");
    }

    public CheckoutPage startCheckout() {
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public CartItem getItem(int number) {
        return new CartItem(number);
    }

    public long getTotalSum() {
        return getLong("//div[contains(@class,'sum-price')]");
    }
}