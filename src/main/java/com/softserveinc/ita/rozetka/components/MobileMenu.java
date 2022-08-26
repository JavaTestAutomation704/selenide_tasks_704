package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;

import static com.codeborne.selenide.Selenide.$x;

import static utils.WebElementUtil.getText;
import static utils.WebElementUtil.isVisible;

public class MobileMenu {
    private final String xpathAuthentication = "//button[contains(@class,'side-menu__auth-button')]";

    public String getLoginButtonName() {
        return getText("(" + xpathAuthentication + ")[1]");
    }

    public String getRegistrationButtonName() {
        return getText("(" + xpathAuthentication + ")[2]");
    }

    public String getHelpCenterButtonName() {
        return getText("//a[contains(@class,'side-menu__button') and contains(@href,'help.')]");
    }

    public String getContactUsButtonName() {
        return getText("//a[contains(@class,'side-menu__button') and contains(@href,'t.me')]");
    }

    public String getCity() {
        SelenideElement citySpan = $x("//span[@class='city-toggle__text']").shouldBe(Condition.visible);
        return citySpan.text();
    }

    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCityModal().changeCity(city);
    }

    public boolean isLanguageSelected(Language language) {
        return isVisible(
                String.format("(//li[contains(@class, 'lang__item')]/span[contains(text(),'%s')])[2]", language));
    }

    public Header changeLanguage(Language language) {
        $x(String.format("//li[contains(@class, 'side-menu')]//*[contains(text(),'%s')]", language)).click();
        return new Header();
    }
}