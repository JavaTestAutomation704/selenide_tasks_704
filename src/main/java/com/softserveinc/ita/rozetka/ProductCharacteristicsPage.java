package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ProductCharacteristicsPage extends BasePage {
    public String getTitle() {
        return $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .text()
                .toLowerCase();
    }

    public boolean isCharacteristicsTabHighlighted() {
        var characteristicsTabTextColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("color");
        var characteristicsTabBottomBorderColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("box-shadow");

        return characteristicsTabTextColor.equals("rgba(0, 160, 70, 1)")
                && characteristicsTabBottomBorderColor.contains("rgb(0, 160, 70)");
    }

    public boolean isCharacteristicsSectionVisible() {
        return isVisible("//rz-product-tab-characteristics");
    }

    @Step("Product characteristics page: add product to comparison list")
    public ProductCharacteristicsPage addToComparison() {
        $x("//app-compare-button").click();
        return this;
    }

    public boolean isComparisonCounterVisible() {
        return isVisible("//rz-comparison//rz-icon-counter");
    }

    public String getCountryName() {
        return $x("//a[contains(@href,'strana')]").text();
    }
}