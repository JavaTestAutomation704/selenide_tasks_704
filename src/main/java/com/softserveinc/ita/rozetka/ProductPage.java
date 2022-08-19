package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ProductPage extends BasePage {

    public CreditModal startPurchaseOnCredit() {
        $x("//app-product-credit").click();
        return new CreditModal();
    }

    public boolean isCreditModalVisible() {
        return isVisible("//credit-modal");
    }
}