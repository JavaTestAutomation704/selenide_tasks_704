package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends EmptyCartTestRunner {
    @Test
    public void addSearchedProductToShoppingCartTest() {
        String searchPhrase = "samsung a52";

        Product firstProduct = homePage
                .getHeader()
                .search(searchPhrase)
                .get(1);

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
}