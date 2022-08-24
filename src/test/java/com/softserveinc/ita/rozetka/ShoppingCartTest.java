package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends TestRunner {
    @Test
    public void deleteProducts() {
        homePage
                .getHeader()
                .search("coffee")
                .addToCart(1)
                .addToCart(5)
                .addToCart(8);

        ShoppingCartModal shoppingCart = homePage
                .getHeader()
                .openShoppingCartModal()
                .remove(2);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(shoppingCart.isEmpty());

        shoppingCart.removeAllProducts();

        softAssert.assertTrue(shoppingCart.isEmpty());
        softAssert.assertAll();
    }
}
