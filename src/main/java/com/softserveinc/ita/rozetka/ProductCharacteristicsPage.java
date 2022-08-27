package com.softserveinc.ita.rozetka;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ProductCharacteristicsPage extends BasePage {
    public String getTitle() {
        return $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .text()
                .toLowerCase();
    }

    public boolean isCharacteristicsTabHighlighted() {
        String characteristicsTabTextColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("color");
        String characteristicsTabBottomBorderColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("box-shadow");

        return characteristicsTabTextColor.equals("rgba(0, 160, 70, 1)")
                && characteristicsTabBottomBorderColor.contains("rgb(0, 160, 70)");
    }

    public ProductCharacteristicsPage addToComparison() {
        $x("//app-compare-button").click();
        return this;
    }

    public boolean isComparisonCounterVisible() {
        return isVisible("//rz-comparison//rz-icon-counter");
    }
}