package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;
import com.softserveinc.ita.rozetka.modals.CatalogModal;

import static utils.WebElementUtil.isVisible;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public boolean isRegisterButtonVisible() {
        return isVisible
                ("//button[@class='auth-modal__register-link button button--link ng-star-inserted']");
    }

    public CatalogModal openCatalogButton(){
        $x("button button--medium button--with-icon menu__toggle ng-star-inserted").click();
        return new CatalogModal();}
}