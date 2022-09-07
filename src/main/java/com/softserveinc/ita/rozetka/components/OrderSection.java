package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public class OrderSection {

    private final int orderNumber;

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

    @Step("Order section: select pickup from Meest")
    public PickupFromMeestSection selectPickupFromMeest() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[4]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromMeestSection(orderNumber);
    }

    @Step("Order section: select pickup from Ukrposhta")
    public PickupFromUkrposhtaSection selectPickupFromUkrposhta() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[5]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromUkrposhtaSection(orderNumber);
    }

    @Step("Order section: select pickup from Nova Poshta")
    public PickupFromNovaPoshtaSection selectPickupFromNovaPoshta() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[5]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromNovaPoshtaSection(orderNumber);
    }

    @Step("Order section: select pickup from mobile delivery point")
    public MobileDeliveryPointSection selectPickupFromMobileDeliveryPoints() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[3]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new MobileDeliveryPointSection(orderNumber);
    }

    @Step("Order section: select pickup from Rozetka")
    public PickupFromRozetkaSection selectPickupFromRozetka() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[1]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        return new PickupFromRozetkaSection(orderNumber);
    }
}
