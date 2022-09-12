package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import com.softserveinc.ita.rozetka.data.goodsExchangePage.EquipmentStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class EquipmentStatusForm extends BaseStatusForm {

    @Step("Equipment status form: select status '{equipmentStatus.getName()}'")
    public EquipmentStatusForm selectStatus(EquipmentStatus equipmentStatus) {
        $x(format(statusXpathTemplate, equipmentStatus.getNumber())).click();
        return this;
    }

    @Step("Equipment status form: open next step")
    public ResultProductForm switchToNextStep() {
        openNext();
        return new ResultProductForm();
    }
}
