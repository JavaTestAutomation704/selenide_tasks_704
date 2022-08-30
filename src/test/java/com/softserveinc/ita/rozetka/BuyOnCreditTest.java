package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.modals.CreditModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class BuyOnCreditTest extends TestRunner {

    @Test
    public void verifyPurchaseOnCreditCapability() {

        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search("Xbox")
                .getFilter()
                .filter(ROZETKA_SELLER);

        int productNumber = 1;

        assertThat(searchResultsPage.getProductsQuantity())
                .as(String.format("There should be at least %d product on the search results page", productNumber))
                .isGreaterThanOrEqualTo(productNumber);

        ProductPage productPage = searchResultsPage
                .getProduct(productNumber)
                .open();

        CreditModal creditModal = productPage.startPurchaseOnCredit();

        assertTrue(creditModal.isOpen());

        SoftAssert softAssert = new SoftAssert();

        boolean isCreditPageOpen = creditModal
                .openCreditPage()
                .isOpen();

        softAssert.assertTrue(isCreditPageOpen);

        Selenide.back();
        productPage.startPurchaseOnCredit();

        boolean isCheckoutPageOpen = creditModal
                .selectCreditVariant(1)
                .isOrderModalVisible();

        softAssert.assertTrue(isCheckoutPageOpen);

        softAssert.assertAll();
    }
}