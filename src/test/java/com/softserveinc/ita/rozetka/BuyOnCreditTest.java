package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static org.testng.Assert.assertTrue;

public class BuyOnCreditTest extends TestRunner {

    @Test
    public void verifyPurchaseOnCreditCapability() {

        boolean isCreditModalOpen = homePage
                .getHeader()
                .search("Xbox")
                .getFilter()
                .filter(ROZETKA_SELLER)
                .get(1)
                .open()
                .startPurchaseOnCredit()
                .isOpen();

        assertTrue(isCreditModalOpen);
    }
}