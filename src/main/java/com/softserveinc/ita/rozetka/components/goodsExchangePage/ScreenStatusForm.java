package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class ScreenStatusForm extends BaseStatusForm {

    @Step("Screen status form: select status 'There are absolutely no signs of use'")
    public ScreenStatusForm selectStatusNoUseSigns() {
        selectRadioButton(1);
        return this;
    }

    @Step("Screen status form: select status 'Chips, cracks, broken anti-glare coating'")
    public ScreenStatusForm selectStatusDamaged() {
        selectRadioButton(2);
        return this;
    }

    @Step("Screen status form: open next step")
    public ImageStatusForm openNextStep() {
        openNext();
        return new ImageStatusForm();
    }
}
