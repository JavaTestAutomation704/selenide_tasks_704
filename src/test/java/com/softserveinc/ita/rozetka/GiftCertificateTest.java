package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.models.CertificateData;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.DeliveryType.*;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.switchToTab;
import static org.assertj.core.api.Assertions.assertThat;

public class GiftCertificateTest extends BaseTestRunner {

    @Test
    public void verifyGiftCertificateTransferFunctionality() {
        var header = homePage.getHeader();
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }

        var giftCertificatesPage = homePage
                .getServiceSection()
                .openGiftCertificatesPage();

        assertThat(giftCertificatesPage.isOpened())
                .as("Gift certificates page should be opened")
                .isTrue();

        var electronicGiftCertificatesTransferSection = giftCertificatesPage.openElectronicGiftCertificatesTransferSection();

        assertThat(electronicGiftCertificatesTransferSection.isOpened())
                .as("Electronic gift certificates transfer section should be opened")
                .isTrue();

        var giftCertificatesTransferPage = electronicGiftCertificatesTransferSection.openGiftCertificatesTransferPage();

        switchToTab(1);

        assertThat(giftCertificatesTransferPage.isOpened())
                .as("Gift certificate transfer page should be opened")
                .isTrue();

        var invalidCertificateData = CertificateData
                .builder()
                .ownerPhone("631234")
                .futureOwnerPhone("633456")
                .code("GYTR")
                .build();

        giftCertificatesTransferPage.fillInTransferForm(invalidCertificateData);

        var softly = new SoftAssertions();

        softly.assertThat(giftCertificatesTransferPage.isToGiftButtonDisabled())
                .as("To gift button should be disabled")
                .isTrue();

        giftCertificatesTransferPage.clearCertificateField();

        var emptyCertificateFieldErrorMessage = giftCertificatesTransferPage.getCertificateErrorMessage();

        softly.assertThat(emptyCertificateFieldErrorMessage)
                .as("Error message should appear")
                .isEqualTo("Поле обов'язкове для заповнення");

        softly.assertThat(giftCertificatesTransferPage.isCertificateFieldBorderColorCorrect())
                .as("Certificate field border color should be correct")
                .isTrue();

        var invalidCodeCertificateData = CertificateData
                .builder()
                .ownerPhone("631122345")
                .futureOwnerPhone("631133456")
                .code("ABGYRTYUERTYUIOP")
                .build();

        giftCertificatesTransferPage.fillInTransferForm(invalidCodeCertificateData);

        assertThat(giftCertificatesTransferPage.isToGiftButtonDisabled())
                .as("To gift button should be enabled")
                .isFalse();

        giftCertificatesTransferPage.submitGiftTransferForm();

        var certificateFieldErrorMessage = giftCertificatesTransferPage.getCertificateErrorMessage();

        softly.assertThat(certificateFieldErrorMessage)
                .as("Error message should appear")
                .isEqualTo("Номер сертифіката або номер телефону власника не вірні. Перевірте коректність даних");

        softly.assertThat(giftCertificatesTransferPage.isCertificateFieldBorderColorCorrect())
                .as("Certificate field border color should be correct")
                .isTrue();

        softly.assertAll();
    }

    private CheckoutPage openCheckoutPage() {
        var keyword = "starbucks";
        var header = homePage.getHeader();
        var searchResultsPage = header.search(keyword);

        assertThat(searchResultsPage.doesTitleContain(keyword))
                .as("Title should contain keyword")
                .isTrue();

        var filter = searchResultsPage.getFilter();
        filter.filter(ROZETKA_SELLER);

        assertThat(filter.isSelected(ROZETKA_SELLER))
                .as("Filter should be selected")
                .isTrue();

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(3);

        for (int i = 1; i <= 3; i++) {
            var product = searchResultsPage.getProduct(i);
            if (product.isAvailable()) {
                product.addToShoppingCart();
                assertThat(product.isInShoppingCart())
                        .as("Product should added to the shopping cart")
                        .isTrue();
                break;
            }
        }

        var shoppingCartModal = header.openShoppingCartModal();

        assertThat(shoppingCartModal.isOpened())
                .as("Shopping cart modal should be opened")
                .isTrue();

        return shoppingCartModal.startCheckout();
    }

    @Test
    public void verifyAddCertificateOptionIsAvailableOnlyWithCertainShippingMethods() {
        var checkoutPage = openCheckoutPage();
        var orderSection = checkoutPage.getOrderSection(1);
        var rozetkaPickUpSection = orderSection.getRozetkaPickUpSection();

        var softly = new SoftAssertions();
        softly.assertThat(rozetkaPickUpSection.isSelected(UA, ROZETKA_PICK_UP))
                .as("Rozetka pickup option should be selected")
                .isTrue();

        var certificateSection = orderSection.getCertificateSection();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be available")
                .isTrue();

        var courierDeliverySection = orderSection.selectCourierDelivery();

        softly.assertThat(courierDeliverySection.isSelected(UA, COURIER_DELIVERY_SECTION))
                .as("Courier delivery option should be selected")
                .isTrue();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be available")
                .isTrue();

        var mobilePointsPickUpSection = orderSection.selectMobilePointPickUp(UA);

        softly.assertThat(mobilePointsPickUpSection.isSelected(UA, MOBILE_POINT_PICK_UP))
                .as("Mobile point pickup option should be selected")
                .isTrue();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be unavailable")
                .isFalse();

        var meestPickUpSection = orderSection.selectMeestPickUp(UA);

        softly.assertThat(meestPickUpSection.isSelected(UA, MEEST_PICK_UP))
                .as("Meest point pickup option should be selected")
                .isTrue();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be unavailable")
                .isFalse();

        var ukrPoshtaPickUpSection = orderSection.selectUkrPoshtaPickUp(UA);

        assertThat(ukrPoshtaPickUpSection.isSelected(UA, UKR_POSHTA_PICK_UP))
                .as("Urk poshta pickup option should be selected")
                .isTrue();

        orderSection.selectPaymentUponReceipt();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be unavailable")
                .isFalse();

        var novaPoshtaPickUpSection = orderSection.selectNovaPoshtaPickUp(UA);

        softly.assertThat(novaPoshtaPickUpSection.isSelected(UA, NOVA_POSHTA_PICK_UP))
                .as("Meest point pickup option should be selected")
                .isTrue();

        softly.assertThat(certificateSection.isAddCertificateOptionAvailable())
                .as("Add certificate option should be available")
                .isTrue();

        softly.assertAll();
    }
}