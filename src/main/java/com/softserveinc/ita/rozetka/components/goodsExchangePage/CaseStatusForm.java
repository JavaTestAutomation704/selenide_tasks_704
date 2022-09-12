package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import com.softserveinc.ita.rozetka.data.goodsExchangePage.CaseStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CaseStatusForm extends BaseStatusForm {

    @Step("Case status form: select status '{caseStatus}'")
    public CaseStatusForm selectStatus(CaseStatus caseStatus) {
        $x(format(statusNameXpathTemplate, caseStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Case status form: open next step")
    public EquipmentStatusForm switchToNextStep() {
        openNext();
        return new EquipmentStatusForm();
    }
}
