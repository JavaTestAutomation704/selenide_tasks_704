package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserveinc.ita.rozetka.data.Category.GAMER_PRODUCTS;
import static com.softserveinc.ita.rozetka.data.Subcategory.MONITORS;

public class CheckoutTest extends TestRunner {

    @Test
    public void VerifyCheckoutPageComponentsVisible() {
        ShoppingCartModal shoppingCart = homePage
                .openCategoryPage(GAMER_PRODUCTS)
                .openSubcategoryPage(MONITORS)
                .get(1)
                .addToShoppingCart()
                .getHeader()
                .openShoppingCartModal();
        long totalSum = shoppingCart.getTotalSum();
        CheckoutPage checkoutPage = shoppingCart.startCheckout();
        long orderTotalSum = checkoutPage.getTotalSum();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderTotalSum, totalSum,
                "Total sum in shopping cart should be equals total sum in checkout page");
        softAssert.assertTrue(checkoutPage.isHeaderVisible(), "Header should be visible");
        softAssert.assertTrue(checkoutPage.isOrderModalVisible(), "Order modal should be visible");
        softAssert.assertTrue(checkoutPage.isContactsModalVisible(), "Contacts modal should be visible");
        softAssert.assertTrue(checkoutPage.isTotalModalVisible(), "Total modal should be visible");
        softAssert.assertAll();
    }
}