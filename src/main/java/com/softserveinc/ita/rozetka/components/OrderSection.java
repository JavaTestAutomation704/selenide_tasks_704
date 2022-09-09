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

    public ContactInformation getRecipientContactInformation() {
        var surname = $x("//rz-checkout-order-recipient//input[@formcontrolname='surname']").val();
        var name = $x("//rz-checkout-order-recipient//input[@formcontrolname='name']").val();
        var phone = $x("//rz-checkout-order-recipient//input[@formcontrolname='phone']").val();

        return ContactInformation.builder()
                .surname(surname)
                .name(name)
                .phone(phone)
                .build();
    }
}