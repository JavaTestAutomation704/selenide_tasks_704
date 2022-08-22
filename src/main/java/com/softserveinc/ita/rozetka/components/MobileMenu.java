package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;

import static com.codeborne.selenide.Selenide.$x;

public class MobileMenu {
    public String getCity() {
        SelenideElement citySpan = $x("//span[@class='city-toggle__text']").shouldBe(Condition.visible);
        return citySpan.text();
    }

    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCityModal().changeCity(city);
    }
}