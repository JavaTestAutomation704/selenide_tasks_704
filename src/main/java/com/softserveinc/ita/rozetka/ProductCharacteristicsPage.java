package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class ProductCharacteristicsPage extends BasePage {
    public String getTitle() {
        return $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .text()
                .toLowerCase();
    }

    private SelenideElement waitCharacteristicsTabToBeActive() {
        var characteristicsTab = $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]");
        characteristicsTab.shouldBe(attributeMatching("class", ".*active.*"), Duration.ofSeconds(10));
        return characteristicsTab;
    }

    public String getCharacteristicsTabTextRgbColor() {
        return waitCharacteristicsTabToBeActive().getCssValue("color");
    }

    public String getCharacteristicsTabUnderscoreRgbColor() {
        return waitCharacteristicsTabToBeActive().getCssValue("box-shadow");
    }

    public boolean isCharacteristicsSectionVisible() {
        return isVisible("//rz-product-tab-characteristics");
    }

    @Step("Product characteristics page: add product to comparison list")
    public ProductCharacteristicsPage addToComparison() {
        var comparisonIconCounter = $x("//rz-comparison//rz-icon-counter");
        var initialCounterValue = isVisible(comparisonIconCounter, 2)
                ? getNumber(comparisonIconCounter)
                : 0;

        $x("//app-compare-button//button").click();

        waitText(comparisonIconCounter, String.valueOf(initialCounterValue + 1));
        return this;
    }

    public boolean isComparisonCounterVisible() {
        return isVisible("//rz-comparison//rz-icon-counter");
    }

    public String getCountryName() {
        return $x("//a[contains(@href,'strana')]").text();
    }
}