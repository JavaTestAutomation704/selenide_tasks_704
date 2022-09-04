package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.City;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class PickupPointsCityPage extends BasePage {

    public boolean isOpened() {
        return isVisible("//section[@id = 'pickup']");
    }

    @Step("Pickup points city page: select city {city}")
    public PickupPointsCityPage selectCity(City city) {
        $x(String.format("//a[contains(text(), '%s')]", city.getCity())).click();
        return this;
    }

    public boolean isSelected(City city) {
        return isVisible(String.format("//h1[contains(text(), 'Точки видачі Rozetka в місті %s')]", city.getCity())) &
                isVisible(String.format("//h1[contains(text(), 'Точки самовивозу Rozetka в місті %s')]", city.getCity()));
    }

    public List<String> getDeliveryPointsAddresses() {
        var elementsAddressesList = $$x("//p[@class = 'map-popup__address']");
        List<String> deliveryPointsAddresses = new ArrayList<>();
        elementsAddressesList.spliterator().forEachRemaining(location -> deliveryPointsAddresses.add(location.text()));
        return deliveryPointsAddresses;
    }

    public List<String> getPickupPointsAddresses() {
        var elementsAddressesList = $$x("//p[contains(@class, 'shop__address--bold')]");
        List<String> pickupPointsAddresses = new ArrayList<>();
        elementsAddressesList.spliterator().forEachRemaining(location -> pickupPointsAddresses.add(location.text()));
        return pickupPointsAddresses;
    }
}
