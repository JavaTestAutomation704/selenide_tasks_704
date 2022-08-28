package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductCounterTest extends TestRunner {

    @Test
    public void verifyProductCounterWorks() {
        Header header = homePage.getHeader();
        boolean doesShoppingCartContainsProducts = header.isShoppingCartCounterVisible();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(doesShoppingCartContainsProducts)
                .as("Shopping cart is empty")
                .isFalse();

        Product product = header
                .search("kipling")
                .getProduct(1);
        product.addToShoppingCart();

        long price = product.getPrice();

        CartItem cartItem = header
                .openShoppingCartModal()
                .getItem(1);

        int initialQuantity = cartItem.getQuantity();
        cartItem.increment();
        int increasedQuantity = cartItem.getQuantity();

        softly.assertThat(increasedQuantity)
                .as("Quantity increased by 1")
                .isEqualTo(initialQuantity + 1);
        softly.assertThat(price + price)
                .as(" Total price increased by product`s price")
                .isEqualTo(cartItem.getTotalPrice());

        cartItem.decrement();
        int decreasedQuantity = cartItem.getQuantity();

        softly.assertThat(decreasedQuantity)
                .as("Quantity decreased by 1")
                .isEqualTo(increasedQuantity - 1);
        softly.assertThat(price)
                .as(" Total price decreased by product`s price")
                .isEqualTo(cartItem.getTotalPrice());
        softly.assertAll();
    }
}
