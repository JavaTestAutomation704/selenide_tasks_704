package com.softserveinc.ita.rozetka.components.order.delivery.section;

import com.softserveinc.ita.rozetka.data.DeliveryType;
import com.softserveinc.ita.rozetka.data.Language;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@RequiredArgsConstructor
public abstract class BaseDeliverySection {

    protected final int orderNumber;

    public boolean isSelected(Language selectedLocalization, DeliveryType deliveryType) {
        var radioButtonXpathTemplate = "(//div[@class = 'checkout-order'])[%d]//span[contains(text(),'%s')]" +
                "/ancestor::div[@class='checkout-variant__radio']//input";
        return $x(format(radioButtonXpathTemplate, orderNumber,
                deliveryType.getDeliveryName(selectedLocalization))).isSelected();
    }
}