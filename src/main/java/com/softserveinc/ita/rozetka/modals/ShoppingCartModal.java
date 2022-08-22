package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.components.CartItem;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.getText;

public class ShoppingCartModal {

    public CheckoutPage startCheckout() {
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public CartItem getCartItem(int id) {
        return new CartItem(id);
    }

    public long getTotalSum() {
        return Long.parseLong(getText("//div[contains(@class,'sum-price')]/span[1]"));
    }
}