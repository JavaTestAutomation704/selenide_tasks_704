package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.modals.ChangeCity;

import static com.codeborne.selenide.Selenide.$x;

public class MobileMenu {
    // implement
    public String getCity() {
        $x("//span[@class='city-toggle__text']").shouldBe(Condition.visible);
        return $x("//span[@class='city-toggle__text']").text();
    }

    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCity().changeCity(city);
    }
}