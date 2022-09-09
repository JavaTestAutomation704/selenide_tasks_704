package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class ChargingStatusForm extends BaseStatusForm {

    @Step("Charging status form: select status 'Less than 100 recharge cycles'")
    public ChargingStatusForm selectStatusLessThanHundredRechargeCycles() {
        selectRadioButton(1);
        return this;
    }

    @Step("Charging status form: select status 'More than 750 recharge cycles'")
    public ChargingStatusForm selectStatusMoreThanSevenHundredRechargeCycles() {
        selectRadioButton(2);
        return this;
    }

    @Step("Charging status form: open next step")
    public ScreenStatusForm openNextStep() {
        openNext();
        return new ScreenStatusForm();
    }
}
