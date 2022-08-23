package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;

import com.softserveinc.ita.rozetka.HomePage;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public ShoppingCartModal openShoppingCartModal() {
        $x("//rz-cart[@class='header-actions__component']/button").click();
        return new ShoppingCartModal();
    }

    public boolean isShoppingCartCounterVisible() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]");
    }

    public HomePage openHomePageViaLogo() {
        $x(("//a[@class='header__logo']")).click();
        return new HomePage();
    }
}