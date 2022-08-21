package com.softserveinc.ita.rozetka.components;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static utils.WebElementUtil.isVisible;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MobileMenu {
    String xpathAuthentication = "//button[contains(@class,'side-menu__auth-button')]";

    public boolean isLoginButtonVisible() {
        return isVisible("(" + xpathAuthentication + ")[1]");
    }

    public boolean isRegistrationButtonVisible() {
        return isVisible("(" + xpathAuthentication + ")[2]");
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