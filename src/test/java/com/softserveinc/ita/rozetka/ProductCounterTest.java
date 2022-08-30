package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductCounterTest extends TestRunner {

    @Test
    public void verifyProductCounterWorks() {
        Header header = homePage.getHeader();
        boolean doesShoppingCartContainProducts = header.isShoppingCartCounterVisible();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(doesShoppingCartContainProducts)
                .as("Shopping cart should be empty")
                .isFalse();
        SearchResultsPage searchResultsPage = header
                .search("kipling");

        softly.assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        Product product = searchResultsPage
                .getProduct(1);
        product.addToShoppingCart();

        softly.assertThat(product.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        long price = product.getPrice();

        CartItem cartItem = header
                .openShoppingCartModal()
                .getItem(1);

        int initialQuantity = cartItem.getQuantity();
        cartItem.increment();
        int increasedQuantity = cartItem.getQuantity();

        softly.assertThat(increasedQuantity)
                .as("Quantity should be increased")
                .isEqualTo(initialQuantity + 1);
        softly.assertThat(price + price)
                .as(" Total price should be increased")
                .isEqualTo(cartItem.getTotalPrice());

        cartItem.decrement();
        int decreasedQuantity = cartItem.getQuantity();

        softly.assertThat(decreasedQuantity)
                .as("Quantity should be decreased")
                .isEqualTo(increasedQuantity - 1);
        softly.assertThat(price)
                .as(" Total price should be decreased")
                .isEqualTo(cartItem.getTotalPrice());
        softly.assertAll();
    }
}
