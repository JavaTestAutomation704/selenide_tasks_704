package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.CreditPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CreditModal {

    @Step("Credit modal: open")
    public CreditModal open() {
        $x("//rz-product-credit").hover().click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//credit-modal");
    }

    @Step("Credit modal: open credit page")
    public CreditPage openCreditPage() {
        $x("//a[contains(@class, 'caption-link')]").click();
        return new CreditPage();
    }

    @Step("Credit modal: select credit variant {number}")
    public CheckoutPage selectCreditVariant(int number) {
        $x(String.format("(//rz-credit-variant//div//button)[%d]", number)).click();
        return new CheckoutPage();
    }
}