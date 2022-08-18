package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public boolean isShoppingCartEmpty() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]");
    }
}