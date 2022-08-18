package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$x;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public ShoppingCartModal openShoppingCart(){
        $x("//rz-cart[@class='header-actions__component']/button").click();
        return new ShoppingCartModal();
    }
}