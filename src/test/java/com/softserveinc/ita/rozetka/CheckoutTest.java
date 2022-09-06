package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.COTTAGE_GARDEN_BACKYARD;
import static com.softserveinc.ita.rozetka.data.Category.GAMERS_GOODS;
import static com.softserveinc.ita.rozetka.data.City.DNIPRO;
import static com.softserveinc.ita.rozetka.data.City.LVIV;
import static com.softserveinc.ita.rozetka.data.DeliveryTypes.*;
import static com.softserveinc.ita.rozetka.data.PostOffice.*;
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
        var checkoutPage = homePage
                .openCategoryPage(COTTAGE_GARDEN_BACKYARD)
                .openSubcategoryPage(GARDEN_TECHNIQUES)
                .getProduct(1)
                .addToShoppingCart()
                .getHeader()
                .openShoppingCartModal()
                .startCheckout();

        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order modal should be visible")
                .isTrue();

        int orderNumber = 1;

        var pickupFromMeestSection = checkoutPage
                .getOrderSection()
                .selectPickupFromMeest(orderNumber);

        assertThat(checkoutPage.getDeliveryTitle(1))
                .as("Delivery should be correct")
                .contains(PICK_UP_FROM_MEEST.getDeliveryNameInUa());

        pickupFromMeestSection.changeCity(LVIV.getCityNameUa());

        pickupFromMeestSection.selectPickupPoint(orderNumber, 6);

        var pickupPointName = pickupFromMeestSection.getPickupPointName(orderNumber);

        var pickupPointModal = pickupFromMeestSection.openPickupPointModal(orderNumber);

        var softAssertions = new SoftAssertions();
        softAssertions.assertThat(pickupPointModal.getTextAtActivePickupPoint())
                .as("Pickup point should be correct")
                .contains(MEEST.getPostName())
                .contains(pickupPointName);

        pickupPointName = pickupPointModal
                .moveToPickupPoint(NOVAPOSHTA.getPostName(), 1)
                .getTextAtActivePickupPoint();

        pickupPointModal.selectActivePickupPoint();

        assertThat(checkoutPage.getDeliveryTitle(1))
                .as("Delivery should be correct")
                .contains(PICK_UP_FROM_NOVAPOSHTA.getDeliveryNameInUa());

        var pickupPointNameActual = checkoutPage
                .getOrderSection()
                .selectPickupFromNovaPoshta(orderNumber)
                .getPickupPointName(orderNumber);

        softAssertions.assertThat(pickupPointName)
                .as("Pickup point should be correct")
                .contains(pickupPointNameActual);


        var pickupFromMobileDeliveryPoints = checkoutPage
                .getOrderSection()
                .selectPickupFromMobileDeliveryPoints(orderNumber);

        assertThat(checkoutPage.getDeliveryTitle(1))
                .as("Delivery should be correct")
                .contains(PICK_UP_FROM_MOBILE_POINTS.getDeliveryNameInUa());

        pickupFromMobileDeliveryPoints.changeCity(DNIPRO.getCityNameUa());

        pickupFromMobileDeliveryPoints.selectPickupPoint(orderNumber, 1);

        pickupPointName = pickupFromMobileDeliveryPoints.getPickupPointName(orderNumber);

        pickupPointModal = pickupFromMobileDeliveryPoints.openPickupPointModal(orderNumber);

        softAssertions.assertThat(pickupPointModal.getTextAtActivePickupPoint())
                .as("Pickup point should be correct")
                .contains(MOBILE_POINT.getPostName())
                .contains(pickupPointName);

        pickupPointName = pickupPointModal
                .moveToPickupPoint(OUR_STORE.getPostName(), 1)
                .getTextAtActivePickupPoint();

        pickupPointModal.selectActivePickupPoint();

        assertThat(checkoutPage.getDeliveryTitle(1))
                .as("Delivery should be correct")
                .contains(PICK_UP_FROM_OUR_STORES.getDeliveryNameInUa());

        pickupPointNameActual = checkoutPage
                .getOrderSection()
                .selectPickupFromOurStores(orderNumber)
                .getPickupPointName(orderNumber);

        softAssertions.assertThat(pickupPointName)
                .as("Pickup point should be correct")
                .contains(pickupPointNameActual);
        softAssertions.assertAll();
    }
}
