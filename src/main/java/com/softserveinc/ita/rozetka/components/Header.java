package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.CatalogModal;

import static com.codeborne.selenide.Selenide.$;
import static utils.WebElementUtil.isVisible;

public class Header {
    public boolean isRegisterButtonVisible() {
        return isVisible("//button[@class='auth-modal__register-link button button--link ng-star-inserted']");
    }
    public CatalogModal openCatalog(){
        $("button button--medium button--with-icon menu__toggle ng-star-inserted").click();
        return new CatalogModal();}
}