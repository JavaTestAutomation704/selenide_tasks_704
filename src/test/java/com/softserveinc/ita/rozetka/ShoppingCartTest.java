package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest extends TestRunner {
    @Test
    public void verifyDeleteProductsFromShoppingCart() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header.search("coffee");

        assertThat(searchResultsPage.getProductsSize())
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
