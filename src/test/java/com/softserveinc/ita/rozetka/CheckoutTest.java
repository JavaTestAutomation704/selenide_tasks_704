package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.GOODS_FOR_GAMERS;
import static com.softserveinc.ita.rozetka.data.subcategory.page.GoodsForGamers.MONITORS;
import static org.assertj.core.api.Assertions.*;

public class CheckoutTest extends TestRunner {

    @Test
    public void verifyCheckoutPageComponents() {
        SubcategoryPage subcategoryPage = homePage
                .openCategoryPage(GOODS_FOR_GAMERS)
                .openSubcategoryPage(MONITORS);
        int productsAmount = 60;

        assertThat(productsAmount)
                .as("Product quantity should be sufficient")
                .isLessThanOrEqualTo(subcategoryPage.getProductsQuantity());

        for (int i = 10; i <= productsAmount; i += 10) {
            subcategoryPage
                    .getProduct(i)
                    .addToShoppingCart();
        }
        ShoppingCartModal shoppingCart = subcategoryPage
                .getHeader()
                .openShoppingCartModal();
        long shoppingCartTotalSum = shoppingCart.getTotalSum();
        CheckoutPage checkoutPage = shoppingCart.startCheckout();
        long checkoutPageTotalSum = checkoutPage.getTotalSum();

        assertThat(checkoutPageTotalSum)
                .as("Shopping cart order sum should be equal to checkout order sum")
                .isEqualTo(shoppingCartTotalSum);
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