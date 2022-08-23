package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;

import static com.codeborne.selenide.Selenide.$x;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public HomePage openHomePageViaLogo() {
        $x(("//a[@class='header__logo']")).click();
        return new HomePage();
    }
}