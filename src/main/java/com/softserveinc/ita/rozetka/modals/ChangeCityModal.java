package com.softserveinc.ita.rozetka.modals;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.components.Header;

import static com.codeborne.selenide.Selenide.$x;

public class ChangeCityModal {
    public Header changeCity(String city) {
        SelenideElement inputCityField = $x("//input[contains(@class, 'autocomplete__input')]");
        inputCityField.clear();
        inputCityField.setValue(city);

        $x("(//form[contains(@class, 'header-location')]//li)[1]").click();
        $x("//div[@class='header-location__footer']//button").click();

        return new Header();
    }
}