package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ImageStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ImageStatusForm extends BaseStatusForm {

    @Step("Image status form: select status '{imageStatus}'")
    public ImageStatusForm selectStatus(ImageStatus imageStatus) {
        $x(format(statusNameXpathTemplate, imageStatus.getOrderNumber())).click();
        return this;
    }

    @Step("Image status form: switch to next step")
    public CaseStatusForm switchToNextStep() {
        switchToNext();
        return new CaseStatusForm();
    }
}