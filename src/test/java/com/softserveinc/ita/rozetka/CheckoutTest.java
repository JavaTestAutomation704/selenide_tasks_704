package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.GAMER_PRODUCTS;
import static com.softserveinc.ita.rozetka.data.subcategory.page.LaptopsAndComputers.MONITORS;
import static org.assertj.core.api.Assertions.*;

public class CheckoutTest extends TestRunner {

    @Test
    public void verifyCheckoutPageComponents() {
        SubcategoryPage subcategoryPage = homePage
                .openCategoryPage(GAMER_PRODUCTS)
                .openSubcategoryPage(MONITORS);
        int productsAmount = 60;

        Assert.assertTrue(productsAmount <= subcategoryPage.getProductsSize());

        for (int i = 1; i < productsAmount; i += 10) {
            subcategoryPage
                    .getProduct(i)
                    .addToShoppingCart();
        }
        ShoppingCartModal shoppingCart = subcategoryPage
                .getHeader()
                .openShoppingCartModal();
        long totalSum = shoppingCart.getTotalSum();
        CheckoutPage checkoutPage = shoppingCart.startCheckout();
        long orderTotalSum = checkoutPage.getTotalSum();

        assertThat(orderTotalSum).isEqualTo(totalSum);
        assertThat(checkoutPage.isHeaderVisible())
                .as("Header should be visible")
                .isTrue();
        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order modal should be visible")
                .isTrue();
        assertThat(checkoutPage.isContactsModalVisible())
                .as("Contacts modal should be visible")
                .isTrue();
        assertThat(checkoutPage.isTotalModalVisible())
                .as("Total modal should be visible")
                .isTrue();
    }
}