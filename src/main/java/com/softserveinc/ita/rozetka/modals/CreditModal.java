package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class CreditModal {

    public CreditModal open() {
        $x("//rz-product-credit").click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//credit-modal");
    }
}
