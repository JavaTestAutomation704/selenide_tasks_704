package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class OrderSection {

    @Step("Courier delivery section: select courier delivery")
    public CourierDeliverySection selectCourierDelivery(int orderNumber) {
        $x(String.format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[2]" +
                        "//label[@class = 'checkout-variant__label']",
                orderNumber)).click();
        return new CourierDeliverySection();
    }
}
