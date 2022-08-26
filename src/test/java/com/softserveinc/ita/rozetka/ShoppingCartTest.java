package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends TestRunner {
    Header header;

    @BeforeMethod
    public void clearShoppingCart() {
        header = homePage.getHeader();
        boolean isShoppingCartCounterVisible = header.isShoppingCartCounterVisible();

        if (isShoppingCartCounterVisible) {
            header
                    .openShoppingCartModal()
                    .clear()
                    .close();
        }
    }

    @Test
    public void verifyUserCanAddSearchedProductToShoppingCart() {
        String searchPhrase = "samsung a52";

        SearchResultsPage results = header
                .search(searchPhrase);

        int productNumber = 1;
        while (!results.getProduct(productNumber).isAvailable()
                && productNumber < results.getProductsSize()) {
            productNumber++;
        }

        Product firstAvailableProduct = results.getProduct(productNumber);
        String productTitle = firstAvailableProduct.getTitle();
        long productPrice = firstAvailableProduct.getPrice();

        SoftAssert softAssert = new SoftAssert();
        for (String word : searchPhrase.split(" ")) {
            softAssert.assertTrue(productTitle.contains(word),
                    "First search result title does not contain " + word);
        }

        ProductPage productPage = firstAvailableProduct.open();
        softAssert.assertEquals(productPage.getTitle(), productTitle);
        softAssert.assertEquals(productPage.getPrice(), productPrice);

        ShoppingCartModal cart = productPage.addToCart();
        softAssert.assertEquals(cart.get(1).getTitle(), productTitle);
        softAssert.assertEquals(cart.getTotalSum(), productPrice);
        softAssert.assertAll();
    }

    @Test
    public void verifyDeleteProductsFromShoppingCart() {
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