package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.modals.CreditModal;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyOnCreditTest extends TestRunner {

    @Test
    public void verifyPurchaseOnCreditCapability() {

        ProductPage productPage = homePage
                .getHeader()
                .search("Xbox")
                .getFilter()
                .filter(ROZETKA_SELLER)
                .getProduct(1)
                .open();

        CreditModal creditModal = productPage.startPurchaseOnCredit();

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

        Selenide.back();
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