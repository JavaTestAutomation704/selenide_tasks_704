package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ComparisonPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class ComparisonItem {
    private final String itemXpath;

    public ComparisonItem(int itemNumber) {
        this.itemXpath = format("(//rz-compare-tile)[%d]", itemNumber);
    }

    public String getItemTitle() {
        return getText(itemXpath + "//a");
    }

    public long getItemPrice() {
        return Long.parseLong($x(itemXpath + "//div[contains(@class, 'product__price ')]")
                .getOwnText()
                .replaceAll("[^0-9]", ""));
    }

    @Step("Comparison item: remove item from comparison")
    public ComparisonPage remove() {
        var comparisonIconCounter = $x("//rz-comparison//rz-icon-counter");
        var initialCounterValue = getNumber(comparisonIconCounter);

        $x(itemXpath + "//button[contains(@id, 'comparisonProductActions')]").click();
        $x(itemXpath + "//rz-trash-icon//button").click();

        waitInvisibility(itemXpath);
        waitText(comparisonIconCounter, String.valueOf(initialCounterValue - 1));
        return new ComparisonPage();
    }
}