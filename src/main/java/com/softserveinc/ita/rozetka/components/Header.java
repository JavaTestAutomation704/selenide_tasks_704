package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.LogInModal;

import static com.codeborne.selenide.Selenide.$x;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public LogInModal startLoggingIn() {
        return new LogInModal().open();
    }
}