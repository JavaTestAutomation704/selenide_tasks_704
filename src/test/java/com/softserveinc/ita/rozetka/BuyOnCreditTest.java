package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;

public class BuyOnCreditTest extends TestRunner {

    @Test
    public void verifyPurchaseOnCreditCapability() {

        CreditModal creditModal = homePage
                .getHeader()
                .search("Xbox")
                .getFilter()
                .filter(ROZETKA_SELLER)
                .getProduct(1)
                .open()
                .startPurchaseOnCredit();

        boolean isCreditModalOpen = creditModal.isOpen();

        boolean isCreditPageOpen = creditModal
                .readDetailedInfo()
                .isOpen();

        boolean isCheckoutPageOpen = creditModal
                .chooseCreditVariant(1)
                .isOrderModalVisible();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isCreditModalOpen);
        softAssert.assertTrue(isCreditPageOpen);
        softAssert.assertTrue(isCheckoutPageOpen);

        softAssert.assertAll();
    }
}