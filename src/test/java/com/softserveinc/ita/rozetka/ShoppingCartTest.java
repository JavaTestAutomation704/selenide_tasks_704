package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest extends TestRunner {
    Header header;

    @BeforeMethod
    public void clearShoppingCart() {
        header = homePage.getHeader();

        if (header.isShoppingCartCounterVisible()) {
            ShoppingCartModal shoppingCartModal = header
                    .openShoppingCartModal()
                    .clear();
            if (shoppingCartModal.isCloseButtonVisible()) {
                shoppingCartModal.close();
            } else {
                homePage.back();
            }
        }
    }

    @Test
    public void verifyUserCanAddSearchedProductToShoppingCart() {
        String searchPhrase = "samsung a52";
        SearchResultsPage searchResultsPage = header.search(searchPhrase);

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productNumber = 1;
        while (!searchResultsPage.getProduct(productNumber).isAvailable()
                && productNumber < productsQuantity) {
            productNumber++;
        }

        Product firstAvailableProduct = searchResultsPage.getProduct(productNumber);
        String firstAvailableProductTitle = firstAvailableProduct.getTitleLowerCase();
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
        // Test could fail because button "Remove all" should not be visible
        SearchResultsPage searchResultsPage = header.search("coffee");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(5);

        searchResultsPage
                .getProduct(1)
                .addToShoppingCart()
                .getProduct(5)
                .addToShoppingCart();

        ShoppingCartModal shoppingCart = header
                .openShoppingCartModal()
                .remove(1);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(shoppingCart.isEmpty())
                .as("Shopping cart should not be empty")
                .isFalse();

        softAssertions.assertThat(shoppingCart.isRemoveAllProductsButtonVisible())
                .as("Button 'Remove all' should not be visible")
                .isFalse();

        shoppingCart.remove(1);

        softAssertions.assertThat(shoppingCart.isEmpty())
                .as("Shopping cart should be empty")
                .isTrue();

        softAssertions.assertAll();
    }

    @Test
    public void verifyShoppingCartPriceCalculation() {
        SearchResultsPage searchResultsPage = header.search("starbucks");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        long firstProductPrice = searchResultsPage.getProduct(1).getPrice();
        long secondProductPrice = searchResultsPage.getProduct(2).getPrice();
        long expectedTotalSum = firstProductPrice + secondProductPrice;

        Product firstProduct = searchResultsPage.getProduct(1);
        firstProduct.addToShoppingCart();

        assertThat(firstProduct.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        Product secondProduct = searchResultsPage.getProduct(2);
        secondProduct.addToShoppingCart();

        assertThat(secondProduct.isInShoppingCart())
                .as("Second product should be added to shopping cart")
                .isTrue();

        long actualTotalSum = header
                .openShoppingCartModal()
                .getTotalSum();

        assertThat(actualTotalSum)
                .as("Total sum in shopping cart should be equal to products prices sum")
                .isEqualTo(expectedTotalSum);
    }

    @Test
    public void verifyProductCounterWorks() {
        var header = homePage.getHeader();
        boolean doesShoppingCartContainProducts = header.isShoppingCartCounterVisible();

        var softly = new SoftAssertions();
        softly.assertThat(doesShoppingCartContainProducts)
                .as("Shopping cart should be empty")
                .isFalse();
        var searchResultsPage = header.search("starbucks");

        softly.assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var product = searchResultsPage.getProduct(1);
        product.addToShoppingCart();

        softly.assertThat(product.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        var cartItem = header
                .openShoppingCartModal()
                .getItem(1);

        int initialQuantity = cartItem.getQuantity();
        cartItem.increment();
        int increasedQuantity = cartItem.getQuantity();

        softly.assertThat(increasedQuantity)
                .as("Quantity should be increased")
                .isEqualTo(initialQuantity + 1);

        cartItem.decrement();
        int decreasedQuantity = cartItem.getQuantity();

        softly.assertThat(decreasedQuantity)
                .as("Quantity should be decreased")
                .isEqualTo(increasedQuantity - 1);
        softly.assertAll();
    }
}