package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyOnCreditTest extends TestRunner {

    @Test
    public void verifyPurchaseOnCreditCapability() {
        var searchResultsPage = homePage
                .getHeader()
                .search("Xbox")
                .getFilter()
                .filter(ROZETKA_SELLER);

        int productNumber = 1;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("The products quantity should be sufficient on the search results page")
                .isGreaterThanOrEqualTo(productNumber);

        var productPage = searchResultsPage
                .getProduct(productNumber)
                .open();

        assertThat(productPage.isBuyOnCreditButtonVisible())
                .as("Buy on credit button should be displayed on the product page")
                .isTrue();

        var creditModal = productPage.startPurchaseOnCredit();

        assertThat(creditModal.isOpened())
                .as("Credit modal should be opened")
                .isTrue();

        var softly = new SoftAssertions();

        var isCreditPageOpened = creditModal
                .openCreditPage()
                .isOpened();

        softly.assertThat(isCreditPageOpened)
                .as("Credit page should be opened")
                .isTrue();

        homePage.back();
        productPage.startPurchaseOnCredit();

        var isCheckoutPageOpened = creditModal
                .selectCreditVariant(1)
                .isOrderModalVisible();

        softly
                .assertThat(isCheckoutPageOpened)
                .as("Checkout page should be opened")
                .isTrue();

        softly.assertAll();
    }
}