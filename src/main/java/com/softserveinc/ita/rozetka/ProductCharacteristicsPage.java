package com.softserveinc.ita.rozetka;

import static com.codeborne.selenide.Selenide.$x;

public class ProductCharacteristicsPage extends BasePage {
    public String getTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public boolean isCharacteristicsTabHighlighted() {
        String characteristicsTabTextColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("color");
        String characteristicsTabBottomBorderColor = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").getCssValue("box-shadow");

        return characteristicsTabTextColor.equals("rgba(0, 160, 70, 1)")
                && characteristicsTabBottomBorderColor.contains("rgb(0, 160, 70)");
    }
}