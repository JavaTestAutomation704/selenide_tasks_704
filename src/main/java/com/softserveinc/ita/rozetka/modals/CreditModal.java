package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.CreditPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class CreditModal {

    @Step("Credit modal: open")
    public CreditModal open() {
        var buyOnCreditButtonXpath = "//rz-product-credit";
        waitTillVisible(buyOnCreditButtonXpath);
        $x(buyOnCreditButtonXpath).hover().click();
        return this;
    }

    public boolean isOpened() {
        return isVisible("//credit-modal");
    }

    @Step("Credit modal: open credit page")
    public CreditPage openCreditPage() {
        $x("//a[contains(@class, 'caption-link')]").click();
        return new CreditPage();
    }

    @Step("Credit modal: select credit variant {number}")
    public CheckoutPage selectCreditVariant(int number) {
        $x(format("(//rz-credit-variant//div//button)[%d]", number)).click();
        waitTillCheckoutPreloaderInvisible();
        return new CheckoutPage();
    }
}