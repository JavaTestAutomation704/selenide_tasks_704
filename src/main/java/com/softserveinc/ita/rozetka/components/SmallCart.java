package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class SmallCart {

    public boolean isOpen() {
        return isVisible("//rz-app-small-cart");
    }

    public HomePage close() {
        $x("//button[contains(@class, 'main-notification__close')]").click();
        return new HomePage();
    }
}
