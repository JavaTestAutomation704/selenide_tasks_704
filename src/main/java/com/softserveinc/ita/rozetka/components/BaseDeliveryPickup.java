package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.PickupPointModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public abstract class BaseDeliveryPickup {

    private final int orderNumber;

    public BaseDeliveryPickup(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPickupPointName() {
        return $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).text();
    }

    @Step("Base delivery pickup: select pickup point department №{departmentNumber}")
    public PickupFromMeestSection selectPickupPointDepartment(int departmentNumber) {
        $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).click();
        $x(format("//ul[contains(@class,'list-inner')]//div[contains(text(),'%d')]", departmentNumber)).click();
        return new PickupFromMeestSection(orderNumber);
    }

    @Step("Base delivery pickup: select pickup point department on {departmentAddress}")
    public PickupFromMeestSection selectPickupPointDepartment(String departmentAddress) {
        $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).click();
        $x(format("//ul[contains(@class,'list-inner')]//div[contains(text(),'%s')]", departmentAddress)).click();
        return new PickupFromMeestSection(orderNumber);
    }

    @Step("Base delivery pickup: open pickup point modal")
    public PickupPointModal openPickupPointModal() {
        $x(format("(//button[contains(@class,'delivery-pickups__map')])[%d]", orderNumber)).click();
        return new PickupPointModal();
    }

    @Step("Base delivery pickup: change city to {city}")
    public Header changeCity(String city) {
        $x("//span[@class='deliveries__city-title']").click();
        return new ChangeCityModal().changeCity(city);
    }
}
