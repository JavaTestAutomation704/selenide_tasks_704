package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeGoodsTest extends TestRunner {

    @Test
    public void verifyGoodsExchangeFunctionality() {
        var goodsExchangePage = homePage
                .openGoodsExchangePage();

        assertThat(goodsExchangePage.isTradeingCalculatorVisible())
                .as("Trading calculator should be visible")
                .isTrue();
        goodsExchangePage.selectBrand("APPLE")
                .selectCategory("Mac")
                .selectModel("MacBook 12")
                .selectStorage("2017 / 1.3 GHz Intel Core i5 / 512 GB / 8 GB")
                .selectColor("Gold");
        var maxPrice = goodsExchangePage.getMaxPrice();
    }

}
