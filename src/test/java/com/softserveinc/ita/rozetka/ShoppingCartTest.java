package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.assertj.core.api.SoftAssertions;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

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

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productNumber = 1;
        while (!searchResultsPage.getProduct(productNumber).isAvailable()
                && productNumber < productsQuantity) {
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
        softly.assertThat(cart.get(1).getTitle())
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
                .withFailMessage("Shopping cart is empty")
                .isFalse();
        softAssertions.assertThat(shoppingCart.isRemoveAllProductsButtonVisible())
                .withFailMessage("Button \"Remove all\" is visible")
                .isFalse();

        shoppingCart.remove(1);

        softAssertions.assertThat(shoppingCart.isEmpty())
                .withFailMessage("Shopping cart isn't empty")
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
}