package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.data.goods_exchange_page.ExchangeProduct;
import com.softserveinc.ita.rozetka.data.goods_exchange_page.ScreenStatus;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class ExchangeProductForm extends BaseStatusForm {

    @Step("Exchange product form: select product you want to exchange for '{exchangeProduct}'")
    public ExchangeProductForm selectStatus(ExchangeProduct exchangeProduct) {
        if (isVisible("//input[@type='radio']")) {
            $x(format(statusNameXpathTemplate, exchangeProduct.getOrderNumber())).click();
        }
        return this;
    }

    @Step("Exchange product form: switch to next step")
    public ResultProductForm switchToNextStep() {
        if (isVisible("//input[@type='radio']")) {
            switchToNext();
        }
        return new ResultProductForm();
    }
}
