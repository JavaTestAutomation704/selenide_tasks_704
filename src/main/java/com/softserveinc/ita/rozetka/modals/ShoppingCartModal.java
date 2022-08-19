package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {

    public ShoppingCartModal increaseProductQuantity(){
        $x("//button[@data-testid='cart-counter-increment-button']").click();
        return this;
    }

    public ShoppingCartModal decreaseProductQuantity(){
        $x("//button[@data-testid='cart-counter-decrement-button']").click();
        return this;
    }

    public int getPrice(int productNumber){
        return Integer.parseInt($x("//p[@data-testid='cost']").text());
    }

    public int getProductQuantity(){
        return Integer.parseInt($x("//input[@data-testid='cart-counter-input']").text());
    }
}