package com.softserveinc.ita.rozetka;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getNumber;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class GoodsExchangePage {

    public GoodsExchangePage selectBrand(String brandName) {
        $x("//select[@id='selectBrand']").click();
        $x(format("//option[@value='%s']", brandName)).click();
        return this;
    }

    public GoodsExchangePage selectCategory(String categoryName) {
        $x("//select[@id='selectCategory']").click();
        $x(format("//option[@value='%s']", categoryName)).click();
        return this;
    }

    public GoodsExchangePage selectModel(String modelName) {
        $x("//select[@id='selectModel']").click();
        $x(format("//option[@value='%s']", modelName)).click();
        return this;
    }

    public GoodsExchangePage selectStorage(String storageDescription) {
        $x("//select[@id='selectStorage']").click();
        $x(format("//option[@value='%s']", storageDescription)).click();
        return this;
    }

    public GoodsExchangePage selectColor(String color) {
        $x("//select[@id='selectColor']").click();
        $x(format("//option[@value='%s']", color)).click();
        return this;
    }

    public long getMaxPrice() {
        return getNumber("//span[@class='price']");
    }

    public boolean isTradeingCalculatorVisible() {
        return isVisible("//div[@class='select-product']");
    }
}
