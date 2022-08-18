package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public Header logInViaUserIcon() {
        $x("//rz-user").click();
        return this;
    }
}