package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import com.softserveinc.ita.rozetka.data.goodsExchangePage.ChargingStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ChargingStatusForm extends BaseStatusForm {

    @Step("Charging status form: select status '{chargingStatus}'")
    public ChargingStatusForm selectStatus(ChargingStatus chargingStatus) {
        $x(format(statusNameXpathTemplate, chargingStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Charging status form: open next step")
    public ScreenStatusForm switchToNextStep() {
        openNext();
        return new ScreenStatusForm();
    }
}
