package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {
    public CheckoutPage makeOrder(){
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public int getCartReceipt() {
        return 0;
    }

    public boolean isShoppingCartEmpty(){
        return false;
    }

    public ShoppingCartModal removeProduct(){
        return this;
    }

    public ShoppingCartModal increaseProductQuantity(){
        return this;
    }

    public ShoppingCartModal decreaseProductQuantity(){
        return this;
    }

    public int getProductQuantity(){
        return 0;
    }

    public String getTitle(int product) {
        return $x(String.format("(//a[@class='cart-product__title'])[%d]",product)).text();
    }

    public int getPrice(int productNumber){
        return 0;
    }
}