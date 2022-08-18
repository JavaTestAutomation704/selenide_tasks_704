package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {
    public String getTitle(int product) {
        return $x(String.format("(//a[@class='cart-product__title'])[%d]",product)).text();
    }
}