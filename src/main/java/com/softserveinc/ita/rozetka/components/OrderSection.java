package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class OrderSection {

    @Step("Courier delivery section: select courier delivery")
    public CourierDeliverySection selectCourierDelivery(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[2]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new CourierDeliverySection();
    }

    @Step("Pickup from Meest: select pickup from Meest")
    public PickupFromMeestSection selectPickupFromMeest(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[4]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromMeestSection();
    }

    @Step("Pickup from Nova Poshta: select pickup from Nova Poshta")
    public PickupFromNovaPoshtaSection selectPickupFromNovaPoshta(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[5]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromNovaPoshtaSection();
    }

    @Step("Mobile delivery point: select pickup from mobile delivery point")
    public MobileDeliveryPointSection selectPickupFromMobileDeliveryPoints(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[3]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new MobileDeliveryPointSection();
    }

    @Step("Mobile delivery point: select pickup from mobile delivery point")
    public PickupFromOurStoresSection selectPickupFromOurStores(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[1]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromOurStoresSection();
    }
}
