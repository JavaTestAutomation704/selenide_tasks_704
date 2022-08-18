package com.softserveinc.ita.rozetka.components;

import static utils.WebElementUtil.isVisible;

public class MobileMenu {

    public boolean isAuthorizationButtonVisible() {
        return isVisible("//button[contains(text(),'Вхід')]");
    }

    public boolean isRegistrationButtonVisible() {
        return isVisible("//button[contains(text(),'Реєстрація')]");
    }

    public boolean isLocationSelectionVisible() {
        return isVisible("//button[contains(@class,'city-toggle')]");
    }

    public boolean isHelpCenterButtonVisible() {
        return isVisible("//a[contains(@class,'side-menu__button') and contains(@href,'help.')]");
    }

    public boolean isContactUsButtonVisible() {
        return isVisible("//a[contains(@class,'side-menu__button') and contains(@href,'t.me')]");
    }
}