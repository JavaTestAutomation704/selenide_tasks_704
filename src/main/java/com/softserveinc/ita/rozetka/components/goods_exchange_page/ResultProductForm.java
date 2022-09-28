package com.softserveinc.ita.rozetka.components.goods_exchange_page;

import com.softserveinc.ita.rozetka.GoodsExchangePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getNumber;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

public class ResultProductForm {

    public long getMaxPrice() {
        return getNumber("//span[@class='price']");
    }

    public String getProductName() {
        return getText("//div[@class='selected-product-name']");
    }

    @Step("Result product form: start over")
    public GoodsExchangePage startOver() {
        $x("//button[contains(@class,'start-over')]").click();
        return new GoodsExchangePage();
    }
}