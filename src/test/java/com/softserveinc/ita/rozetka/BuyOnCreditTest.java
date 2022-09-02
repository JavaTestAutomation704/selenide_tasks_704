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
                .as(String.format("There should be at least %d product on the search results page", productNumber))
                .isGreaterThanOrEqualTo(productNumber);

        var productPage = searchResultsPage
                .getProduct(productNumber)
                .open();

        var creditModal = productPage.startPurchaseOnCredit();

        assertThat(creditModal.isOpen())
                .as("Credit modal should be open")
                .isTrue();

        SoftAssertions softAssert = new SoftAssertions();

        boolean isCreditPageOpen = creditModal
                .openCreditPage()
                .isOpen();

        softAssert.assertThat(isCreditPageOpen)
                .as("Credit page should be open")
                .isTrue();

        homePage.back();
        productPage.startPurchaseOnCredit();

        boolean isCheckoutPageOpen = creditModal
                .selectCreditVariant(1)
                .isOrderModalVisible();

        softAssert
                .assertThat(isCheckoutPageOpen)
                .as("Checkout page should be open")
                .isTrue();

        softAssert.assertAll();
    }
}