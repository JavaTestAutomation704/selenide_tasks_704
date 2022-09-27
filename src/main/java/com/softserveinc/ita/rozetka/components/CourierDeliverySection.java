package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public class CourierDeliverySection {

    @NonNull
    private int orderNumber;
    private final String streetFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'title']";
    private final String houseFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'house']";
    private final String flatFieldXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//input[@formcontrolname = 'flat']";

    public boolean isOpened() {
        return isVisible("(//ul//rz-checkout-order-delivery)[2]//fieldset");
    }

    @Step("Courier delivery section: fill in delivery details {street}, {house}, {flat}")
    public CourierDeliverySection fillInDeliveryDetails(String street, String house, String flat) {
        fillInStreetField(street);
        $x(format(houseFieldXpathTemplate, orderNumber)).sendKeys(house);
        $x(format(flatFieldXpathTemplate, orderNumber)).sendKeys(flat);
        waitTillCheckoutPreloaderInvisible();
        return this;
    }

    @Step("Courier delivery section: fill in {street}")
    private CourierDeliverySection fillInStreetField(String street) {
        $x(format(streetFieldXpathTemplate, orderNumber)).sendKeys(street);
        var streetDropDownListXpath = "(//rz-checkout-dropdown)[1]//div[@role = 'button']";
        if (!isVisible(streetDropDownListXpath)) {
            var streetLength = $x(format(streetFieldXpathTemplate, orderNumber)).getValue().length();
            //TODO: loop below is solving the problem with street drop down list field autocomplete
            for (int i = streetLength; i > 3; i--) {
                $x(format(streetFieldXpathTemplate, orderNumber)).sendKeys(Keys.BACK_SPACE);
            }
        }
        waitTillVisible(streetDropDownListXpath);
        $x(streetDropDownListXpath).click();
        return this;
    }

    @Step("Courier delivery section: select nearest possible date")
    public CourierDeliverySection selectNearestPossibleDate() {
        $x(format("((//div[@class = 'checkout-order'])[%d]//label[@class = 'delivery-intervals__time'])[1]",
                orderNumber)).click();
        waitTillCheckoutPreloaderInvisible();
        return this;
    }

    public boolean isDeliveryDataCopiedTo(int orderNumber) {
        return Objects.equals($x(format(streetFieldXpathTemplate, this.orderNumber)).getValue(),
                $x(format(streetFieldXpathTemplate, orderNumber)).getValue()) &&
                Objects.equals($x(format(houseFieldXpathTemplate, this.orderNumber)).getValue(),
                        $x(format(houseFieldXpathTemplate, orderNumber)).getValue()) &&
                Objects.equals($x(format(flatFieldXpathTemplate, this.orderNumber)).getValue(),
                        $x(format(flatFieldXpathTemplate, orderNumber)).getValue());
    }
}