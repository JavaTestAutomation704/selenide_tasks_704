package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.getText;

@RequiredArgsConstructor
public class CartItem {
    private final int id;
    private String quantityInputXpath = "(//input[contains(@class, 'cart-counter')])[%d]";

    public String getTitle() {
        return getText(String.format("(//a[contains(@class, 'cart-product__title')])[%d]", id)).toLowerCase();
    }

    public int getQuantity() {
        return Integer.parseInt(getText(String.format(quantityInputXpath, id)));
    }

    public ShoppingCartModal increment() {
        $x(String.format("(//button[contains(@data-testid, 'increment')])[%d]", id)).click();
        return new ShoppingCartModal();
    }

    public ShoppingCartModal decrement() {
        $x(String.format("(//button[contains(@data-testid, 'decrement')])[%d]", id)).click();
        return new ShoppingCartModal();
    }

    public ShoppingCartModal setQuantity(String quantity) {
        SelenideElement quantityInput = $x(String.format(quantityInputXpath, id));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        return new ShoppingCartModal();
    }

    public long getTotalPrice() {
        return Long.parseLong(getText(String.format("(//input[contains(@class, 'cart-counter')])[%d]", id)));
    }
}
