package com.softserveinc.ita.rozetka;

import org.testng.annotations.BeforeMethod;

public class EmptyCartTestRunner extends TestRunner{
    @BeforeMethod
    public void clearShoppingCart() {
        boolean isShoppingCartCounterVisible = homePage
                .getHeader()
                .isShoppingCartCounterVisible();

        if (isShoppingCartCounterVisible) {
            homePage
                    .getHeader()
                    .openShoppingCartModal()
                    .clear()
                    .close();
        }
    }
}