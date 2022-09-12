package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SellOnRozetkaPage {

    private final String fillInApplicationLinkXpath = "(//a[contains(@href, '/registration')])[1]";

    public boolean isOpened() {
        return isVisible(fillInApplicationLinkXpath);
    }

    @Step("Sell on rozetka page: open seller registration page")
    public SellerRegistrationPage openSellerRegistrationPage() {
        $x(fillInApplicationLinkXpath).click(ClickOptions.usingJavaScript());
        return new SellerRegistrationPage();
    }
}