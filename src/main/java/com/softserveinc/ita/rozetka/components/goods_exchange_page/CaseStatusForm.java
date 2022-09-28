package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.CaseStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CaseStatusForm extends BaseStatusForm {

    @Step("Case status form: select status '{caseStatus}'")
    public CaseStatusForm selectStatus(CaseStatus caseStatus) {
        $x(format(statusNameXpathTemplate, caseStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Case status form: switch to next step")
    public EquipmentStatusForm switchToNextStep() {
        switchToNext();
        return new EquipmentStatusForm();
    }
}