package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ScreenStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ScreenStatusForm extends BaseStatusForm {

    @Step("Screen status form: select status '{screenStatus}'")
    public ScreenStatusForm selectStatus(ScreenStatus screenStatus) {
        $x(format(statusNameXpathTemplate, screenStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Screen status form: switch to next step")
    public ImageStatusForm switchToNextStep() {
        switchToNext();
        return new ImageStatusForm();
    }
}
