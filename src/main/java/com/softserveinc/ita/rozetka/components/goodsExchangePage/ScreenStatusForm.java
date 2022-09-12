package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import com.softserveinc.ita.rozetka.data.goodsExchangePage.ScreenStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ScreenStatusForm extends BaseStatusForm {

    @Step("Screen status form: select status '{screenStatus.getName()}'")
    public ScreenStatusForm selectStatus(ScreenStatus screenStatus) {
        $x(format(statusXpathTemplate, screenStatus.getNumber())).click();
        return this;
    }

    @Step("Screen status form: open next step")
    public ImageStatusForm switchToNextStep() {
        openNext();
        return new ImageStatusForm();
    }
}
