package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.City;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitForTextChange;

public class PickupPointsCityPage extends BasePage {

    private final String deliveryAddressXpath = "//p[@class = 'map-popup__address']";

    public boolean isOpened() {
        return isVisible("//section[@id = 'pickup']");
    }

    @Step("Pickup points city page: select city {city}")
    public PickupPointsCityPage selectCity(City city) {
        var firstDeliveryAddressElement = $x(deliveryAddressXpath);
        String address = firstDeliveryAddressElement.text();
        $x(String.format("(//a[contains(@href, 'retail/%s') and contains(@class, 'tags__link')])",
                city.getCity())).click();
        waitForTextChange(deliveryAddressXpath, address);
        return this;
    }

    public boolean isSelected(City city) {
        String titleXpathTemplate = "(//h1[contains(text(), '%s') or contains(text(), '%s')])";
        return isVisible(String.format(titleXpathTemplate + "[1]", city.getCityNameUa(), city.getCityNameRu()))
                & isVisible(String.format(titleXpathTemplate + "[2]", city.getCityNameUa(), city.getCityNameRu()));
    }

    public List<String> getDeliveryPointsAddresses() {
        return $$x(deliveryAddressXpath).texts();
    }

    public List<String> getPickupPointsAddresses() {
        return $$x("//p[contains(@class, 'shop__address--bold')]").texts();
    }
}