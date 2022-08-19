package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.LogInModal;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class Header {
    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public LogInModal startLoggingIn() {
        $x("//rz-user").click();
        return new LogInModal();
    }

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }
}