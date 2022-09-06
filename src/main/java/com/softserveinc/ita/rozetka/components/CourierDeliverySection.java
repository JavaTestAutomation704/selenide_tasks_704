package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.Color;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isBorderColorCorrect;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CourierDeliverySection {

    private int orderNumber;
    private final String streetFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'title']";
    private final String houseFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'house']";
    private final String flatFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'flat']";

    public CourierDeliverySection(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isOpened() {
        return isVisible("(//ul//rz-checkout-order-delivery)[2]//fieldset");
    }

    @Step("Courier delivery section: fill in delivery details {street}, {house}, {flat}")
    public CourierDeliverySection fillInDeliveryDetails(String street, String house, String flat) {
        $x(String.format(streetFieldXpathTemplate, orderNumber)).val(street).pressEnter();
        $x(String.format(houseFieldXpathTemplate, orderNumber)).val(house).pressEnter();
        $x(String.format(flatFieldXpathTemplate, orderNumber)).val(flat).pressEnter();
        return this;
    }

    public boolean areCopiedTo(int orderNumber) {
        return Objects.equals($x(String.format(streetFieldXpathTemplate, this.orderNumber)).getValue(),
                $x(String.format(streetFieldXpathTemplate, orderNumber)).getValue()) &&
                Objects.equals($x(String.format(houseFieldXpathTemplate, this.orderNumber)).getValue(),
                        $x(String.format(houseFieldXpathTemplate, orderNumber)).getValue()) &&
                Objects.equals($x(String.format(flatFieldXpathTemplate, this.orderNumber)).getValue(),
                        $x(String.format(flatFieldXpathTemplate, orderNumber)).getValue());
    }
}
