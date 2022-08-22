package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {

    public ShoppingCartModal increaseProductQuantity(int productNumber) {
        $x(String.format("//(button[@data-testid='cart-counter-increment-button'])[%s]", productNumber)).click();
        return this;
    }

    public ShoppingCartModal decreaseProductQuantity(int productNumber) {
        $x(String.format("//(button[@data-testid='cart-counter-decrement-button'])[%s]", productNumber)).click();
        return this;
    }

    public int getPrice(int productNumber) {
        return Integer.parseInt($x(String.format("//(p[@data-testid='cost'])[%s]", productNumber)).text());
    }

    public int getProductQuantity(int productNumber) {
        return Integer.parseInt($x(String.format("//(input[@data-testid='cart-counter-input)[%s]", productNumber)).text());
    }
}