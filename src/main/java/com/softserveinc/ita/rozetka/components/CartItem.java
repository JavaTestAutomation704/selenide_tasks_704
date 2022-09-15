package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.Long.getLong;
import static java.lang.String.format;

public class CartItem {
    private final String cartItemXpath;

    public CartItem(int cartItemNumber) {
        cartItemXpath = format("(//rz-cart-product)[%d]", cartItemNumber);
    }

    public String getTitle() {
        return getText(cartItemXpath + "//a[contains(@class, 'cart-product__title')]").toLowerCase();
    }

    public int getQuantity() {
        return Integer.parseInt($x(cartItemXpath + "//input[contains(@class, 'cart-counter')]").val());
    }

    @Step("Cart item: increase the product quantity by one")
    public ShoppingCartModal increment() {
        $x(cartItemXpath + "//button[contains(@data-testid, 'increment')]").click();
        return new ShoppingCartModal();
    }

    @Step("Cart item: decrease the product quantity by one")
    public ShoppingCartModal decrement() {
        $x(cartItemXpath + "//button[contains(@data-testid, 'decrement')]").click();
        return new ShoppingCartModal();
    }

    @Step("Shopping cart modal: remove product with number {productNumber}")
    public ShoppingCartModal remove() {
        $x(cartItemXpath + "//button[contains(@id, 'Actions')]")
                .scrollIntoView(false)
                .click();
        $x("//button[contains(@class, 'context-menu-actions__button')]").click();
        return new ShoppingCartModal();
    }

    @Step("Cart item: set product quantity {quantity}")
    public ShoppingCartModal setQuantity(String quantity) {
        var quantityInput = $x(cartItemXpath + "//input[contains(@class, 'cart-counter')]");
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        return new ShoppingCartModal();
    }

    public long getTotalPrice() {
        return getLong(cartItemXpath + "//p[contains(@class, 'cart-product__price')]");
    }

    @Step("Shopping cart modal: choose additional service")
    public CartItem addService(int serviceNumber) {
        $x(format("(%s//span[@class='cart-service__title'])[%s]", cartItemXpath, serviceNumber)).click();
        return this;
    }

    public long getAdditionalServicePrice(int serviceNumber) {
        return getLongFromField(format("(%s//span[contains(@class, 'price')]//span[contains(@class, 'cart-service__price')])[%s]", cartItemXpath, serviceNumber));
    }

    public boolean isAdditionalServicesAvailable() {
        return isVisible(cartItemXpath + "//button[@data-testid='cart-services-toggle']");
    }
}