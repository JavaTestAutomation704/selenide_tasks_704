package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

@RequiredArgsConstructor
public class CartItem {
    private final int numberCartItem;
    private String quantityInputXpath = "(//input[contains(@class, 'cart-counter')])[%d]";

    public String getTitle() {
        return getText(String.format("(//a[contains(@class, 'cart-product__title')])[%d]", numberCartItem)).toLowerCase();
    }

    public int getQuantity() {
        return Integer.parseInt($x(String.format(quantityInputXpath, numberCartItem)).val());
    }

    @Step("Shopping cart modal: increase the product quantity by one")
    public ShoppingCartModal increment() {
        $x(String.format("(//button[contains(@data-testid, 'increment')])[%d]", numberCartItem)).click();
        return new ShoppingCartModal();
    }

    @Step("Shopping cart modal: reduce the product quantity by one")
    public ShoppingCartModal decrement() {
        $x(String.format("(//button[contains(@data-testid, 'decrement')])[%d]", numberCartItem)).click();
        return new ShoppingCartModal();
    }

    @Step("Shopping cart modal: set product quantity {quantity}")
    public ShoppingCartModal setQuantity(String quantity) {
        SelenideElement quantityInput = $x(String.format(quantityInputXpath, numberCartItem));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        return new ShoppingCartModal();
    }

    public long getTotalPrice() {
        return Long.parseLong(getText(String.format("(//input[contains(@class, 'cart-counter')])[%d]", numberCartItem)));
    }
}
