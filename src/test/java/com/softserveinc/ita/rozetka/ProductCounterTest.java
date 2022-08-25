package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.CartItem;
import com.softserveinc.ita.rozetka.components.Header;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductCounterTest extends TestRunner {

    @Test
    public void verifyProductCounterWorks() {
        Header header = homePage.getHeader();
        boolean isShoppingCartEmpty = header.isShoppingCartCounterVisible();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isShoppingCartEmpty);

        CartItem cartItem = header
                .search("kipling")
                .getProduct(1)
                .addToShoppingCart()
                .getHeader()
                .openShoppingCartModal()
                .getItem(1);

        int startQuantity = cartItem.getQuantity();
        long price = cartItem.getTotalPrice();
        int increasedQuantity = cartItem
                .increment()
                .getItem(1)
                .getQuantity();

        softAssert.assertTrue(startQuantity + 1 == increasedQuantity);
        softAssert.assertTrue(price + price == cartItem.getTotalPrice());

        int decreasedQuantity = cartItem
                .decrement()
                .getItem(1)
                .getQuantity();

        softAssert.assertTrue(decreasedQuantity == increasedQuantity - 1);
        softAssert.assertTrue(price == cartItem.getTotalPrice());
    }
}
