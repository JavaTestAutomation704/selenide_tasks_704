package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends TestRunner {
    Header header;

    @BeforeMethod
    public void clearShoppingCart() {
        header = homePage.getHeader();

        if (header.isShoppingCartCounterVisible()) {
            header
                    .openShoppingCartModal()
                    .clear()
                    .close();
        }
    }

    @Test
    public void verifyUserCanAddSearchedProductToShoppingCart() {
        String searchPhrase = "samsung a52";
        SearchResultsPage searchResultsPage = header.search(searchPhrase);

        int productsSize = searchResultsPage.getProductsQuantity();
        int productNumber = 1;
        while (!searchResultsPage.getProduct(productNumber).isAvailable()
                && productNumber < productsSize) {
            productNumber++;
        }

        Product firstAvailableProduct = searchResultsPage.getProduct(productNumber);
        String firstAvailableProductTitle = firstAvailableProduct.getTitle();
        long firstAvailableProductPrice = firstAvailableProduct.getPrice();

        SoftAssertions softly = new SoftAssertions();
        for (String word : searchPhrase.split(" ")) {
            softly.assertThat(firstAvailableProductTitle)
                    .as("First available product title should contain searched keyword.")
                    .contains(word);
        }

        ProductPage productPage = firstAvailableProduct.open();
        softly.assertThat(productPage.getTitle())
                .as("Search result title should be equal to Product page title.")
                .isEqualTo(firstAvailableProductTitle);
        softly.assertThat(productPage.getPrice())
                .as("Search result price should be equal to Product page price.")
                .isEqualTo(firstAvailableProductPrice);

        ShoppingCartModal cart = productPage.addToCart();
        if (!cart.isOpened()) {
            header.openShoppingCartModal();
        }
        softly.assertThat(cart.getItem(1).getTitle())
                .as("Product page title should be equal to Cart item title.")
                .isEqualTo(firstAvailableProductTitle);
        softly.assertThat(cart.getTotalSum())
                .as("Product page price should be equal to Cart item price.")
                .isEqualTo(firstAvailableProductPrice);
        softly.assertAll();
    }

    @Test
    public void verifyDeleteProductsFromShoppingCart() {
        SearchResultsPage searchResultsPage = header.search("coffee");

        Assert.assertTrue(searchResultsPage.getProductsQuantity() >= 5);

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