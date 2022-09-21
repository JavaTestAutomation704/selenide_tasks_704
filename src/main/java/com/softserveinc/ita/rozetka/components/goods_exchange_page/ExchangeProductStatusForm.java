package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ExchangeProductStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class ExchangeProductStatusForm extends BaseStatusForm {
    private String buttonNextXpath = "//button[contains(@class,'button-next')]";

    @Step("Exchange product form: select product you want to exchange for '{exchangeProduct}'")
    public ExchangeProductStatusForm selectStatus(ExchangeProductStatus exchangeProduct) {
        if (isVisible(buttonNextXpath)) {
            $x(format(statusNameXpathTemplate, exchangeProduct.getOrderNumber())).click();
        }
        return this;
    }

    @Step("Exchange product form: switch to next step")
    public ResultProductForm switchToNextStep() {
        if (isVisible(buttonNextXpath)) {
            switchToNext();
        }
        return new ResultProductForm();
    }
}
