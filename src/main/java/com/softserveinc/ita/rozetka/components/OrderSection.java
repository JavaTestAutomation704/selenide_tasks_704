package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public class OrderSection {

    @NonNull
    private int orderNumber;

    @Step("Courier delivery section: select courier delivery")
    public CourierDeliverySection selectCourierDelivery() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[2]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new CourierDeliverySection(orderNumber);
    }

    @Step("Order section: copy information to other orders")
    public OrderSection copyToOtherOrders() {
        $x(format("(//rz-copy-order-button)[%d]/button", orderNumber)).click();
        return this;
    }

    public CourierDeliverySection getCourierDeliverySection() {
        return new CourierDeliverySection(orderNumber);
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
