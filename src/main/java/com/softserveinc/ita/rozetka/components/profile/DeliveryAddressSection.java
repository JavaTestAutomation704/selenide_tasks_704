package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.models.DeliveryAddress;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

@RequiredArgsConstructor
public class DeliveryAddressSection {
    private final String deliveryAddressSectionXpath;

    @Step("Delivery address section: start editing")
    public EditDeliveryAddressSection startEditing() {
        $x("(//button[@class='button button--medium button--green personal-data__edit'])[4]").click();
        return new EditDeliveryAddressSection(deliveryAddressSectionXpath);
    }


    public DeliveryAddress getDeliveryAddress() {
        var address = getText("(//li[contains(@class, 'addresses__item')])");

        return DeliveryAddress
                .builder()
                .city(address.split(", ")[0])
                .street(address.split(",")[1])
                .building(address.split(",")[2])
                .flat(address.split(",")[3])
                .build();
    }

    public boolean isOpened() {
        return isVisible("(//div[@class='personal-section__body'])[4]");
    }
}
