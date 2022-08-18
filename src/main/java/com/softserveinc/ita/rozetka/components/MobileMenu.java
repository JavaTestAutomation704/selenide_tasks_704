package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MobileMenu {
    // implement
    public String getCity() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return $x("//span[@class='city-toggle__text']").text();
    }

    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        SelenideElement inputCityField = $x("//input[contains(@class, 'autocomplete__input')]");
        inputCityField.clear();
        inputCityField.setValue(city);

        $x("(//form[contains(@class, 'header-location')]//li)[1]").click();
        $x("//div[@class='header-location__footer']//button").click();

        return new Header();
    }
}