package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;

public class ProductPage extends BasePage {

    public CreditModal startPurchaseOnCredit() {
        return new CreditModal().open();
    }
}