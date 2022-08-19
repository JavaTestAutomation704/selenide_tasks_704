package com.softserveinc.ita.rozetka.modals;


import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ShoppingCartModal {
    public boolean isShoppingCartEmpty() {
        return isVisible("//div[@data-testid='empty-cart']");
    }

    public ShoppingCartModal remove(int productNumber) {
        $x(String.format("//button[@id='cartProductActions%s']", (productNumber - 1))).click();
        $x("//button[contains(@class, 'context-menu-actions__button')]").click();
        return this;
    }

    public ShoppingCartModal removeAllProducts() {
        $x("//div[@class='cart-header__remove']//button").click();
        $x("(//li[contains(@class,'popup-menu')])[1]//button").click();
        return this;
    }
}