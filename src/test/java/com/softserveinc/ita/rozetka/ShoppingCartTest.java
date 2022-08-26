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
        SearchResultsPage searchResultsPage = header.search("coffee");

        Assert.assertTrue(searchResultsPage.getProductsSize() >= 5);

        searchResultsPage
                .getProduct(1)
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


    @Test
    public void verifyShoppingCartPriceCalculation() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header.search("starbucks");

        boolean isShoppingCartEmpty = header.isShoppingCartCounterVisible();
        Assert.assertFalse(isShoppingCartEmpty);

        long firstProductPrice = searchResultsPage.getProduct(1).getPrice();
        long secondProductPrice = searchResultsPage.getProduct(2).getPrice();
        long actualTotalSum = firstProductPrice + secondProductPrice;

        searchResultsPage
                .getProduct(1)
                .addToShoppingCart()
                .getProduct(2)
                .addToShoppingCart();

        long expectedTotalSum = header
                .openShoppingCartModal()
                .getTotalSum();

        Assert.assertEquals(actualTotalSum, expectedTotalSum,
                "Order total sum calculation in shopping cart is not correct");
    }
}
