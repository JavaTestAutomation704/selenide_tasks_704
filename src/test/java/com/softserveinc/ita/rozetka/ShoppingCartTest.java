package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends TestRunner {
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

    @Test
    public void verifyUserCanAddSearchedProductToShoppingCartTest() {
        String searchPhrase = "samsung a52";

        Product firstProduct = homePage
                .getHeader()
                .search(searchPhrase)
                .getProduct(1);

        String firstProductTitle = firstProduct.getTitle();
        long firstProductPrice = firstProduct.getPrice();

        SoftAssert softAssert = new SoftAssert();
        for (String word : searchPhrase.split(" ")) {
            softAssert.assertTrue(firstProductTitle.contains(word),
                    "First search result title does not contain " + word);
        }

        ProductPage firstProductPage = firstProduct.open();
        softAssert.assertEquals(firstProductPage.getTitle(), firstProductTitle);
        softAssert.assertEquals(firstProductPage.getPrice(), firstProductPrice);

        ShoppingCartModal cart = firstProductPage.addToCart();
        softAssert.assertEquals(cart.get(1).getTitle(), firstProductTitle);
        softAssert.assertEquals(cart.getTotalSum(), firstProductPrice);
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