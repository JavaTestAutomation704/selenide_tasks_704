package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class ProductForm {

    @Step("Product form: select brand {brandName}")
    public ProductForm selectBrand(String brandName) {
        $x("//select[@id='selectBrand']").click();
        $x(format("//option[@value='%s']", brandName)).click();
        return this;
    }

    @Step("Product form: select category {categoryName}")
    public ProductForm selectCategory(String categoryName) {
        $x("//select[@id='selectCategory']").click();
        $x(format("//option[@value='%s']", categoryName)).click();
        return this;
    }

    @Step("Product form: select model {modelName}")
    public ProductForm selectModel(String modelName) {
        $x("//select[@id='selectModel']").click();
        $x(format("//option[@value='%s']", modelName)).click();
        return this;
    }

    @Step("Product form: select characteristic {characteristicDescription}")
    public ProductForm selectCharacteristic(String characteristicDescription) {
        $x("//select[@id='selectStorage']").click();
        $x(format("//option[@value='%s']", characteristicDescription)).click();
        return this;
    }

    @Step("Product form: select color {color}")
    public ProductForm selectColor(String color) {
        $x("//select[@id='selectColor']").click();
        $x(format("//option[@value='%s']", color)).click();
        return this;
    }

    public boolean isTradingCalculatorVisible() {
        return isVisible("//div[@class='select-product']");
    }

    public long getMaxPrice() {
        return getNumber("//span[@class='price']");
    }

    public String getProductName() {
        return getText("//div[@class='selected-product-name']");
    }

    @Step("Product form: open next step")
    public FunctionalityStatusForm openNextStep() {
        $x("//button[contains(@class,'go-to-question')]").click();
        return new FunctionalityStatusForm();
    }
}
