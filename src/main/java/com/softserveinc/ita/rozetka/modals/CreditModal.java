package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.CreditPage;

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

    public CreditPage readDetailedInfo() {
        $x("//a[contains(@class, 'caption-link')]").click();
        return new CreditPage();
    }

    public CheckoutPage chooseCreditVariant(int number) {
        $x(String.format("(//rz-credit-variant//div//button)[%d]", number)).click();
        return new CheckoutPage();
    }
}
