package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class EquipmentStatusForm extends BaseStatusForm {
    @Step("Equipment status form: select status 'Full factory equipment without damage'")
    public EquipmentStatusForm selectStatusFullEquipment() {
        selectRadioButton(1);
        return this;
    }

    @Step("Equipment status form: select status 'There is no complete set'")
    public EquipmentStatusForm selectStatusCompleteSetIsMissing() {
        selectRadioButton(3);
        return this;
    }

    @Step("Equipment status form: open next step")
    public ResultProductForm openNextStep() {
        openNext();
        return new ResultProductForm();
    }
}
