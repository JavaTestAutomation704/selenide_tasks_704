package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
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
        $(By.xpath("//button[@data-testid='cart-counter-increment-button']")).click();
        return this;
    }

    public ShoppingCartModal decreaseProductQuantity(){
        $(By.xpath("//button[@data-testid='cart-counter-decrement-button']")).click();
        return this;
    }

    public int getProductQuantity(){
        return Integer.parseInt($("//input[@data-testid='cart-counter-input']").text());
    }

    public String getTitle(int product) {
        return $x(String.format("(//a[@class='cart-product__title'])[%d]",product)).text();
    }

    public int getPrice(int productNumber){
        return Integer.parseInt($("//p[@data-testid='cost']").text());
    }
}