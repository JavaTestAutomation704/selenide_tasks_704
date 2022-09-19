package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.models.ContactInformation;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.data.DeliveryType.*;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public class OrderSection {

    private final int orderNumber;

    @Step("Courier delivery section: select courier delivery")
    public CourierDeliverySection selectCourierDelivery() {
        $x(format("(//div[@class = 'checkout-order'])[%d]//rz-checkout-order-deliveries//li[2]" +
                "//label[@class = 'checkout-variant__label']", orderNumber)).click();
        waitTillCheckoutPreloaderInvisible();
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
    public MeestPickUpSection selectMeestPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]/../../../label",
                orderNumber, MEEST_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillPreloaderInvisible();
        return new MeestPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Nova Poshta")
    public NovaPoshtaPickUpSection selectNovaPoshtaPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]/../../../label",
                orderNumber, NOVA_POSHTA_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillPreloaderInvisible();
        return new NovaPoshtaPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Rozetka")
    public RozetkaPickUpSection selectRozetkaPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]/../../../label",
                orderNumber, ROZETKA_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillPreloaderInvisible();
        return new RozetkaPickUpSection(orderNumber);
    }

    @Step("Order section: change city to {city}")
    public Header changeCity(String city) {
        $x(format("(//span[@class='deliveries__city-title'])[%d]", orderNumber)).click();
        return new ChangeCityModal().changeCity(city);
    }

    public ContactInformation getRecipientContactInformation() {
        var inputSurnameXpath = "//rz-checkout-order-recipient//input[@formcontrolname='surname']";
        var inputNameXpath = "//rz-checkout-order-recipient//input[@formcontrolname='name']";
        var inputPhoneXpath = "//rz-checkout-order-recipient//input[@formcontrolname='phone']";

        return ContactInformation
                .builder()
                .surname($x(inputSurnameXpath).val())
                .name($x(inputNameXpath).val())
                .phone($x(inputPhoneXpath).val())
                .build();
    }
}