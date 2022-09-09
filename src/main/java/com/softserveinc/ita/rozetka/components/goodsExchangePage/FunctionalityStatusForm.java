package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class FunctionalityStatusForm extends BaseStatusForm {
    @Step("Functionality status form: select status 'all functions work properly'")
    public FunctionalityStatusForm selectStatusAllFunctionWorks() {
        selectRadioButton(1);
        return this;
    }

    @Step("Functionality status form: select status 'one or more functions are not working'")
    public FunctionalityStatusForm selectStatusFunctionNotWorks() {
        selectRadioButton(2);
        return this;
    }

    @Step("Functionality status form: open next step")
    public ChargingStatusForm openNextStep() {
        openNext();
        return new ChargingStatusForm();
    }
}
