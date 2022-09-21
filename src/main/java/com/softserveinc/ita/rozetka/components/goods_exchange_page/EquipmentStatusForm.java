package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.EquipmentStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class EquipmentStatusForm extends BaseStatusForm {

    @Step("Equipment status form: select status '{equipmentStatus}'")
    public EquipmentStatusForm selectStatus(EquipmentStatus equipmentStatus) {
        $x(format(statusNameXpathTemplate, equipmentStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Equipment status form: switch to next step")
    public ExchangeProductStatusForm switchToNextStep() {
        switchToNext();
        return new ExchangeProductStatusForm();
    }
}
