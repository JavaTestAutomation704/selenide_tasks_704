package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillPreloaderInvisible;
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
    public MeestPickUpSection selectPickupFromMeest() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[3]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        waitTillPreloaderInvisible();
        return new MeestPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Nova Poshta")
    public NovaPoshtaPickUpSection selectPickupFromNovaPoshta() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[5]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        waitTillPreloaderInvisible();
        return new NovaPoshtaPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Rozetka")
    public RozetkaPickUpSection selectPickupFromRozetka() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[1]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        waitTillPreloaderInvisible();
        return new RozetkaPickUpSection(orderNumber);
    }

    @Step("Base delivery pickup: change city to {city}")
    public Header changeCity(String city) {
        $x(format("(//span[@class='deliveries__city-title'])[%d]", orderNumber)).click();
        return new ChangeCityModal().changeCity(city);
    }
}
