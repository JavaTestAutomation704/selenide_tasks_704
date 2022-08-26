package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends TestRunner {
    @Test
    public void verifyDeleteProductsFromShoppingCart() {
        Header header = homePage.getHeader();
        var searchResultsPage = header
                .search("coffee");

        Assert.assertTrue(searchResultsPage.productSize() >= 5);

        searchResultsPage.getProduct(1)
                .addToShoppingCart()
                .getProduct(5)
                .addToShoppingCart();

        ShoppingCartModal shoppingCart = header
                .openShoppingCartModal()
                .remove(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(shoppingCart.isEmpty(), "Shopping cart is empty");
        softAssert.assertFalse(shoppingCart.isRemoveAllProductsButtonVisible(), "Button \"Remove all\" is visible");

        shoppingCart.remove(1);

        softAssert.assertTrue(shoppingCart.isEmpty(), "Shopping cart isn't empty");
        softAssert.assertAll();
    }
}
