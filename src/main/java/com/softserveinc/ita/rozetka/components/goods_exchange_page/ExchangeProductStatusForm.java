package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ExchangeProductStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class ExchangeProductStatusForm extends BaseStatusForm {

    private final String buttonNextStepXpath = "//button[contains(@class,'button-next')]";

    @Step("Exchange product status form: select product you want to exchange for '{exchangeProductStatus}'")
    public ExchangeProductStatusForm selectStatus(ExchangeProductStatus exchangeProductStatus) {
        if (isVisible(buttonNextStepXpath)) {
            $x(format(statusNameXpathTemplate, exchangeProductStatus.getOrderNumber())).click();
        }
        return this;
    }

    @Step("Exchange product status form: switch to next step")
    public ResultProductForm switchToNextStep() {
        if (isVisible(buttonNextStepXpath)) {
            switchToNext();
        }
        return new ResultProductForm();
    }
}