package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

public class ImageStatusForm extends BaseStatusForm {
    @Step("Image status form: select status 'A perfect image without defects'")
    public ImageStatusForm selectStatusPerfectImage() {
        selectRadioButton(1);
        return this;
    }

    @Step("Image status form: select status 'Color reproduction is broken'")
    public ImageStatusForm selectStatusBrokenColorRendering() {
        selectRadioButton(2);
        return this;
    }

    @Step("Image status form: open next step")
    public CaseStatusForm openNextStep() {
        openNext();
        return new CaseStatusForm();
    }
}
