package com.softserveinc.ita.rozetka.modals;

import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {
    public boolean isShoppingCartEmpty() {
        try {
            return $x("//div[@data-testid='empty-cart']").isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ShoppingCartModal remove(int productNumber) {
        $x("//button[@id='cartProductActions" + (productNumber - 1) + "']").click();
        $x("//button[contains(@class, 'context-menu-actions__button')]").click();
        return this;
    }

    public ShoppingCartModal removeAllProduct(){
        $x("//div[@class='cart-header__remove']//button").click();
        $x("(//li[contains(@class,'popup-menu')])[1]//button").click();
        return this;
    }
}