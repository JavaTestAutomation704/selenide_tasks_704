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

    public CartItem get(int cartItemNumber) {
        return new CartItem(cartItemNumber);
    }

    public long getTotalSum() {
        return Long.parseLong(getText("//div[contains(@class,'sum-price')]/span[1]"));
    }
}