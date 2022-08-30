package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;

import static com.codeborne.selenide.Selenide.$x;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class MobileMenu {
    private final String xpathAuthentication = "//button[contains(@class,'side-menu__auth-button')]";

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

    public String getCity() {
        SelenideElement citySpan = $x("//span[@class='city-toggle__text']").shouldBe(Condition.visible);
        return citySpan.text();
    }

    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCityModal().changeCity(city);
    }

    public HomePage openHomePageViaLogo() {
        $x("//a[contains(@class, 'side-menu__logo')]").click();
        return new HomePage();
    }
}