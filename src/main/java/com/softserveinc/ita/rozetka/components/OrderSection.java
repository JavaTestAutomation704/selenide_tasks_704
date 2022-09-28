package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.components.order.delivery.section.*;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.models.ContactInformation;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.data.DeliveryType.*;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

@RequiredArgsConstructor
public class OrderSection {
    @NonNull
    private int orderNumber;

    public CourierDeliverySection getCourierDeliverySection() {
        return new CourierDeliverySection(orderNumber);
    }

    public RozetkaPickUpSection getRozetkaPickUpSection() {
        return new RozetkaPickUpSection(orderNumber);
    }

    public CertificateSection getCertificateSection() {
        return new CertificateSection(orderNumber);
    }

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

    @Step("Order section: select pickup from Meest")
    public MeestPickUpSection selectMeestPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]//ancestor::label",
                orderNumber, MEEST_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillCheckoutPreloaderInvisible();
        return new MeestPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Mobile point")
    public MobilePointPickUpSection selectMobilePointPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]//ancestor::label",
                orderNumber, MOBILE_POINT_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillCheckoutPreloaderInvisible();
        return new MobilePointPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Ukr Poshta point")
    public UkrPoshtaPickUpSection selectUkrPoshtaPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]//ancestor::label",
                orderNumber, UKR_POSHTA_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillCheckoutPreloaderInvisible();
        return new UkrPoshtaPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Nova Poshta")
    public NovaPoshtaPickUpSection selectNovaPoshtaPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]//ancestor::label",
                orderNumber, NOVA_POSHTA_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillCheckoutPreloaderInvisible();
        return new NovaPoshtaPickUpSection(orderNumber);
    }

    @Step("Order section: select pickup from Rozetka")
    public RozetkaPickUpSection selectRozetkaPickUp(Language selectedLocalization) {
        $x(format("(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]//ancestor::label",
                orderNumber, ROZETKA_PICK_UP.getDeliveryName(selectedLocalization))).click();
        waitTillCheckoutPreloaderInvisible();
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

    @Step("Order section: select payment upon receipt")
    public OrderSection selectPaymentUponReceipt() {
        $x(String.format("((//div[@class = 'checkout-order'])[%d]" +
                "//rz-checkout-order-payments//label)[1]", orderNumber)).click();
        return this;
    }

    @Step("Order section: open certificate section")
    public CertificateSection openCertificateSection() {
        $x(format("(//div[@class = 'checkout-order'])[%d]" +
                "//div[contains(@class, 'certificate-wrap')]/button", orderNumber)).click();
        return new CertificateSection(orderNumber);
    }
}