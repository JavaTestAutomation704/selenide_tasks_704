package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import com.softserveinc.ita.rozetka.data.goodsExchangePage.FunctionalityStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class FunctionalityStatusForm extends BaseStatusForm {

    @Step("Functionality status form: select status '{functionalityStatus}'")
    public FunctionalityStatusForm selectStatus(FunctionalityStatus functionalityStatus) {
        $x(format(statusNameXpathTemplate, functionalityStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Functionality status form: open next step")
    public ChargingStatusForm switchToNextStep() {
        openNext();
        return new ChargingStatusForm();
    }
}
