package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.PickupPointModal;

import static com.codeborne.selenide.Selenide.$x;

public abstract class SelfPickup {
    public String getPickupPointName(int orderNumber) {
        return $x(String.format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).text();
    }

    public PickupFromMeestSection selectPickupPoint(int orderNumber, int index) {
        $x(String.format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).click();
        $x(String.format("(//ul[contains(@class,'list-inner')]/li)[%d]", index)).click();
        return new PickupFromMeestSection();
    }

    public PickupPointModal openPickupPointModal(int orderNumber) {
        $x(String.format("(//button[contains(@class,'delivery-pickups__map')])[%d]", orderNumber)).click();
        return new PickupPointModal();
    }

    public Header changeCity(String city) {
        $x("//span[@class='deliveries__city-title']").click();
        return new ChangeCityModal().changeCity(city);
    }
}
