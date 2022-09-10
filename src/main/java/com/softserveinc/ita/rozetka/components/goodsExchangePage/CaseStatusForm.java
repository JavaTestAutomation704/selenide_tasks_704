package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class CaseStatusForm extends BaseStatusForm {

    @Step("Case status form: select status 'There are absolutely no signs of use'")
    public CaseStatusForm selectStatusNoUseSigns() {
        selectRadioButton(1);
        return this;
    }

    @Step("Case status form: select status 'Broken or deformed'")
    public CaseStatusForm selectStatusBrokenOrDeformed() {
        selectRadioButton(5);
        return this;
    }

    @Step("Case status form: open next step")
    public EquipmentStatusForm openNextStep() {
        openNext();
        return new EquipmentStatusForm();
    }
}
