package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class ShoppingCartModal {
    private final String closeButtonXpath = "//button[contains(@class, 'modal__close')]";

    public boolean isEmpty() {
        return isVisible("//div[@data-testid='empty-cart']");
    }

    @Step("Shopping cart modal: clear shopping cart")
    public ShoppingCartModal clear() {
        for (int i = getCollectionSize("//rz-cart-product"); i > 0; i--) {
            getItem(i).remove();
        }
        return this;
    }

    @Step("Shopping cart modal: close shopping cart modal")
    public Header close() {
        $x(closeButtonXpath).click();
        return new Header();
    }

    public boolean isRemoveAllProductsButtonVisible() {
        return isVisible("//div[@class='cart-header__remove']//button");
    }

    @Step("Shopping cart modal: start checkout and move to checkout page")
    public CheckoutPage startCheckout() {
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public CartItem getItem(int number) {
        return new CartItem(number);
    }

    public long getTotalSum() {
        return getNumber("//div[contains(@class,'sum-price')]");
    }

    public boolean isOpened() {
        return isVisible("//rz-shopping-cart", 3);
    }

    public boolean isCloseButtonVisible() {
        return isVisible(closeButtonXpath);
    }
}