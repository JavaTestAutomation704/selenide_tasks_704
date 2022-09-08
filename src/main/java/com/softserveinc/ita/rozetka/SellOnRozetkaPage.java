package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;

public class SellOnRozetkaPage {

    private final String fillInApplicationLinkXpath = "(//a[contains(@href, '/registration')])";

    public boolean isOpened() {
        waitTillVisible("//footer");
        $x("//footer").scrollIntoView(false);
        return isVisible(fillInApplicationLinkXpath + "[1]") &&
                isVisible(fillInApplicationLinkXpath + "[2]") &&
                isVisible(fillInApplicationLinkXpath + "[3]");
    }

    @Step("Sell on rozetka page: open seller registration page")
    public SellerRegistrationPage openSellerRegistrationPage() {
        $x(fillInApplicationLinkXpath + "[1]").click(ClickOptions.usingJavaScript());
        return new SellerRegistrationPage();
    }
}