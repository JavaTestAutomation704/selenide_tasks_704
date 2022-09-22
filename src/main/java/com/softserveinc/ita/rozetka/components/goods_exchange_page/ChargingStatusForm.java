package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ChargingStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ChargingStatusForm extends BaseStatusForm {

    @Step("Charging status form: select status '{chargingStatus}'")
    public ChargingStatusForm selectStatus(ChargingStatus chargingStatus) {
        $x(format(statusNameXpathTemplate, chargingStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Charging status form: switch to next step")
    public ScreenStatusForm switchToNextStep() {
        switchToNext();
        return new ScreenStatusForm();
    }
}
