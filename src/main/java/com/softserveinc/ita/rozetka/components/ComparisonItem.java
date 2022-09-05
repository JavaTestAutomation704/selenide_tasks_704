package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ComparisonPage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

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
        var initialCounterText = comparisonIconCounter.text();
        $x(itemXpath + "//button[contains(@id, 'comparisonProductActions')]").click();
        $x("//rz-trash-icon//button").click();
        comparisonIconCounter.shouldHave(text(initialCounterText), Duration.ofSeconds(2));
        $x(itemXpath).shouldNotBe(visible, ofSeconds(2));
        return new ComparisonPage();
    }
}