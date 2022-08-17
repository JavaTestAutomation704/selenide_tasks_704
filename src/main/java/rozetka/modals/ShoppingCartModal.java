package rozetka.modals;

import rozetka.CheckoutPage;

public class ShoppingCartModal {

    public CheckoutPage makeOrder(){
        return new CheckoutPage();
    }

    public int getCartReceipt() {
        return 0;
    }
}