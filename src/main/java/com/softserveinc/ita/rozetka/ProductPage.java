package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    public CreditModal startPurchaseOnCredit() {
        $x("//app-product-credit").click();
        return new CreditModal();
    }
}