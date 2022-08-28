package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductCounterTest extends TestRunner {

    @Test
    public void verifyProductCounterWorks() {
        Header header = homePage.getHeader();
        boolean isShoppingCartEmpty = header.isShoppingCartCounterVisible();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isShoppingCartEmpty);

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

        softAssert.assertTrue(initialQuantity + 1 == increasedQuantity, "1");
        softAssert.assertTrue(price + price == cartItem.getTotalPrice(), "2");

        cartItem.decrement();
        int decreasedQuantity = cartItem.getQuantity();

        softAssert.assertTrue(decreasedQuantity == increasedQuantity - 1, "3");
        softAssert.assertTrue(price == cartItem.getTotalPrice(), "4");
        softAssert.assertAll();
    }
}
