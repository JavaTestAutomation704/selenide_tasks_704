package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getLong;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

@RequiredArgsConstructor
public class CartItem {
    private final int numberCartItem;
    private final String quantityInputXpath = "(//input[contains(@class, 'cart-counter')])[%d]";

    public String getTitle() {
        return getText(String.format("(//a[contains(@class, 'cart-product__title')])[%d]", numberCartItem)).toLowerCase();
    }

    public int getQuantity() {
        return Integer.parseInt($x(String.format(quantityInputXpath, numberCartItem)).val());
    }

    @Step("Cart item: increase the product quantity by one")
    public ShoppingCartModal increment() {
        $x(String.format("(//button[contains(@data-testid, 'increment')])[%d]", numberCartItem)).click();
        return new ShoppingCartModal();
    }

    @Step("Cart item: decrease the product quantity by one")
    public ShoppingCartModal decrement() {
        $x(String.format("(//button[contains(@data-testid, 'decrement')])[%d]", numberCartItem)).click();
        return new ShoppingCartModal();
    }

    @Step("Cart item: set product quantity {quantity}")
    public ShoppingCartModal setQuantity(String quantity) {
        var quantityInput = $x(String.format(quantityInputXpath, numberCartItem));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        return new ShoppingCartModal();
    }

    public long getTotalPrice() {
        var priceXpath = String.format("(//p[contains(@class, 'cart-product__price')])[%d]", numberCartItem);
        $x(priceXpath).shouldNotHave(Condition.exactText($x(priceXpath).text()));
        return getLong(priceXpath);
    }
}
