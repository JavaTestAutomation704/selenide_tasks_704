package com.softserveinc.ita.rozetka;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTest extends TestRunner {

    @Test
    public void verifyShoppingCartPriceCalculation() {
        homePage
                .getHeader()
                .search("starbucks");

        boolean isShoppingCartEmpty = homePage
                .getHeader()
                .isShoppingCartCounterVisible();
        Assert.assertFalse(isShoppingCartEmpty);

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        long firstProductPrice = searchResultsPage.getProduct(1).getPrice();
        long secondProductPrice = searchResultsPage.getProduct(2).getPrice();
        long actualTotalSum = firstProductPrice + secondProductPrice;

        searchResultsPage
                .getProduct(1)
                .addToShoppingCart()
                .getProduct(2)
                .addToShoppingCart();

        long expectedTotalSum = homePage
                .getHeader()
                .openShoppingCartModal()
                .getTotalSum();

        Assert.assertEquals(actualTotalSum, expectedTotalSum,
                "Order total sum calculation in shopping cart is not correct");
    }
}
