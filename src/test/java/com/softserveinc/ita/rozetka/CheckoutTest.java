package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.COTTAGE_GARDEN_BACKYARD;
import static com.softserveinc.ita.rozetka.data.Category.GAMERS_GOODS;
import static com.softserveinc.ita.rozetka.data.DeliveryTypes.PICK_UP_FROM_MEEST;
import static com.softserveinc.ita.rozetka.data.DeliveryTypes.PICK_UP_FROM_UKRPOSHTA;
import static com.softserveinc.ita.rozetka.data.subcategory.CottageGardenBackyardSubcategory.GARDEN_TECHNIQUES;
import static com.softserveinc.ita.rozetka.data.subcategory.GamersGoodsSubcategory.MONITORS;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends TestRunner {

    @Test
    public void verifyCheckoutFunctionalityWorks() {
        var subcategoryPage = homePage
                .openCategoryPage(GAMERS_GOODS)
                .openSubcategoryPage(MONITORS);
        int productsQuantity = 60;

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 10; i <= productsQuantity; i += 10) {
            subcategoryPage
                    .getProduct(i)
                    .addToShoppingCart();
        }
        var shoppingCart = subcategoryPage
                .getHeader()
                .openShoppingCartModal();
        var shoppingCartTotalSum = shoppingCart.getTotalSum();
        var checkoutPage = shoppingCart.startCheckout();
        var checkoutPageTotalSum = checkoutPage.getTotalSum();

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

    @Test
    public void verifyDeliveryWorksOnCheckoutPage() {
        var shoppingCart = homePage
                .openCategoryPage(COTTAGE_GARDEN_BACKYARD)
                .openSubcategoryPage(GARDEN_TECHNIQUES)
                .getProduct(1)
                .addToShoppingCart()
                .getHeader()
                .openShoppingCartModal();

        var shoppingCartTotalSum = shoppingCart.getTotalSum();
        var checkoutPage = shoppingCart.startCheckout();
        var checkoutPageTotalSum = checkoutPage.getTotalSum();

        assertThat(checkoutPageTotalSum)
                .as("Shopping cart order sum should be equal to checkout order sum")
                .isEqualTo(shoppingCartTotalSum);

        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order modal should be visible")
                .isTrue();

        checkoutPage.changeCity("Львів");

        boolean isDeliverySelected = checkoutPage
                .selectDeliveryType(PICK_UP_FROM_MEEST)
                .isDeliverySelected(PICK_UP_FROM_MEEST);

        assertThat(isDeliverySelected)
                .as("Delivery type should be selected")
                .isTrue();

        var pickupPointName = checkoutPage
                .selectPickupPoint(5)
                .getPickupPointName();

        var pickupPointModal = checkoutPage.openPickupPointModal();

        var softAssertions = new SoftAssertions();
        softAssertions.assertThat(pickupPointModal.getTextAtActivePickupPoint())
                .as("Pickup point should be correct")
                .contains("Meest")
                .contains(pickupPointName);

        pickupPointName = pickupPointModal
                .moveToPickupPoint("Укрпошта", 1)
                .getTextAtActivePickupPoint();

        pickupPointModal.selectActivePickupPoint();

        softAssertions.assertThat(checkoutPage.isDeliverySelected(PICK_UP_FROM_UKRPOSHTA))
                .as("Delivery type should be selected")
                .isTrue();

        softAssertions.assertThat(pickupPointName)
                .as("Pickup point should be correct")
                .contains(checkoutPage.getPickupPointName());

        softAssertions.assertAll();
    }

}