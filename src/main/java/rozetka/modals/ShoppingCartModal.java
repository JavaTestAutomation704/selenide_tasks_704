package rozetka.modals;

import rozetka.CheckoutPage;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartModal {

    public CheckoutPage makeOrder(){
        $x("//a[contains(@data-testid,'order')]").click();
        return new CheckoutPage();
    }

    public int getCartReceipt() {
        return 0;
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

    public int getPrice(int productNumber){
        return 0;
    }
}